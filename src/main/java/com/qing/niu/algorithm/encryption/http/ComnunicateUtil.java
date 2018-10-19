package com.qing.niu.algorithm.encryption.http;

import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *     交易通信工具类
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/11
 */
@Slf4j
public class ComnunicateUtil {
    private CloseableHttpClient httpclient;
    private RequestConfig requestConfig;
    private PoolingHttpClientConnectionManager connManager;
    private static ComnunicateUtil httpClient4UtilNew;
    private volatile boolean shutdown;

    /**
     * 获取单实例
     *
     * @return 交易通信工具类
     * @throws Exception 异常
     */
    public synchronized static ComnunicateUtil getInstance() throws Exception {
        if (httpClient4UtilNew == null) {
            httpClient4UtilNew = new ComnunicateUtil();
            httpClient4UtilNew.init();
        }
        return httpClient4UtilNew;
    }

    /**
     * 私有化构造方法
     */
    private ComnunicateUtil() {
    }

    /**
     * 初始化
     *
     * @throws NoSuchAlgorithmException 无此算法异常
     * @throws KeyManagementException key管理异常
     */
    @SuppressWarnings("Duplicates")
    public void init() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslCtx = SSLContext.getInstance("TLS");
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }
        };
        sslCtx.init(null, new TrustManager[]{trustManager}, null);
        LayeredConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                sslCtx, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder
                .create();
        ConnectionSocketFactory plainSocketFactory = new PlainConnectionSocketFactory();
        registryBuilder.register("http", plainSocketFactory);
        registryBuilder.register("https", sslSocketFactory);

        Registry<ConnectionSocketFactory> registry = registryBuilder.build();
        // 设置连接管理器
        connManager = new PoolingHttpClientConnectionManager(registry);
        // 设置最大连接数
        connManager.setMaxTotal(1000);
        // 设置每个路由基础的连接
        connManager.setDefaultMaxPerRoute(60);

        // 连接保持活跃策略
        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response,
                                             HttpContext context) {
                // 获取'keep-alive'HTTP报文头
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (null != value && "timeout".equalsIgnoreCase(param)) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                // 保持20秒活跃
                return 20 * 1000;
            }
        };

        httpclient = HttpClientBuilder.create().setConnectionManager(
                connManager).setKeepAliveStrategy(myStrategy).build();
        shutdown = false;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!shutdown) {
                    try {
                        synchronized (this) {
                            wait(500);
                            // 关闭过期的连接
                            connManager.closeExpiredConnections();
                            // 关闭超过40秒的空闲连接
                            connManager.closeIdleConnections(40, TimeUnit.SECONDS);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    /**
     * Http Post请求
     *
     * @param url 请求地址
     * @param formParams 请求参数
     * @param charset
     * @param timeout
     * @return
     * @throws Exception
     */
    public byte[] doPost(String url, List<NameValuePair> formParams, String charset, int timeout) throws Exception {
        return doPost(url, null, null, formParams, null, "UTF-8", timeout);
    }

    /**
     * http post完整请求请求
     *
     * @param url 请求地址
     * @param heard 请求头
     * @param queryParams 头参数
     * @param formParams 请求体
     * @param content 报文
     * @param charset 字符集
     * @param timeout 超时时间
     * @return 响应报文
     * @throws Exception 异常
     */
    @SuppressWarnings("Duplicates")
    public byte[] doPost(String url, Map<String, String> heard, List<NameValuePair> queryParams, List<NameValuePair> formParams, String content, String charset, int timeout) throws Exception {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        CloseableHttpResponse response = null;
        HttpPost httppost = new HttpPost();
        try {
            URIBuilder builder = new URIBuilder(url);
            // 填入查询参数
            if (!CollectionUtils.isEmpty(queryParams)) {
                builder.setParameters(queryParams);
            }
            httppost.setURI(builder.build());
            timeout = Optional.fromNullable(timeout).or(60000);
            requestConfig = RequestConfig.custom()
                    .setConnectTimeout(timeout).setSocketTimeout(
                            timeout).build();
            httppost.setConfig(requestConfig);
            if (null != heard) {
                for (String key : heard.keySet()) {
                    httppost.setHeader(key, heard.get(key));
                }
            }

            if (!CollectionUtils.isEmpty(formParams)) {
                httppost.setEntity(new UrlEncodedFormEntity(formParams, charset));
            }
            if (StringUtils.isNotBlank(content)) {
                httppost.setEntity(new StringEntity(content, charset));
            }
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            int stateCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK != stateCode) {
                new RuntimeException("非正常响应[" + stateCode + "]" + new String(EntityUtils.toByteArray(entity), charset));
            }
            if (entity != null) {
                return EntityUtils.toByteArray(entity);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httppost.releaseConnection();
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    throw new RuntimeException("释放连接异常");
                }
            }
        }
        return null;
    }
}
