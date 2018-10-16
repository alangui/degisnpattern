package web;

import com.baofoo.ps.rsa.RsaCodingUtil;
import com.qing.niu.algorithm.encryption.three_des.ThreeDes;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *     对外服务
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/16
 */
@Slf4j
@Controller
public class WebServiceController {

    /**
     * 上海建行挡板服务
     *
     * @param request 请求
     * @param response 响应
     * @throws Exception 异常
     */
    @RequestMapping(value = "/ccb/withold",method = RequestMethod.POST)
    public void ccbService(HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.info("有服务进入.................................");
        Map<String, String[]> map = request.getParameterMap();
        Set<Map.Entry<String, String[]>> set = map.entrySet();
        for (Map.Entry<String,String[]> entry : set){
            log.info("key:{},value:{}",entry.getKey(),entry.getValue());
        }
        String res = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Transaction>\n" +
                "<Transaction_Header>\n" +
                "<SYS_TX_VRSN><![CDATA[01]]></SYS_TX_VRSN>\n" +
                "<SYS_MSG_LEN><![CDATA[]]></SYS_MSG_LEN>\n" +
                "<SYS_RECV_TIME><![CDATA[20140529144601984]]></SYS_RECV_TIME>\n" +
                "<SYS_RESP_TIME><![CDATA[20140529144701984]]></SYS_RESP_TIME>\n" +
                "<SYS_PKG_STS_TYPE><![CDATA[01]]></SYS_PKG_STS_TYPE>\n" +
                "<SYS_TX_STATUS><![CDATA[00]]></SYS_TX_STATUS>\n" +
                "<SYS_RESP_CODE><![CDATA[00000000000000]]></SYS_RESP_CODE>\n" +
                "<SYS_RESP_DESC_LEN><![CDATA[002]]></SYS_RESP_DESC_LEN>\n" +
                "<SYS_RESP_DESC><![CDATA[成功]]></SYS_RESP_DESC>\n" +
                "<SYS_EVT_TRACE_ID><![CDATA[1010010011399346294138790]]></SYS_EVT_TRACE_ID>\n" +
                "<SYS_SND_SERIAL_NO><![CDATA[0000000000]]></SYS_SND_SERIAL_NO>\n" +
                "<HOST_BUSN_DT><![CDATA[20140529]]></HOST_BUSN_DT>\n" +
                "<TX_LOG_NO><![CDATA[1234567891234567891]]></TX_LOG_NO>\n" +
                "<COMMUNICATE_STATUS><![CDATA[]]></COMMUNICATE_STATUS>\n" +
                "<FLOW_STATUS><![CDATA[]]></FLOW_STATUS>\n" +
                "<ERR_CODE><![CDATA[]]></ERR_CODE>\n" +
                "<ERR_DESCRIPTION_LL><![CDATA[]]></ERR_DESCRIPTION_LL>\n" +
                "<ERR_DESCRIPTION><![CDATA[]]></ERR_DESCRIPTION>\n" +
                "<PAGE_STA_KEY><![CDATA[]]></PAGE_STA_KEY>\n" +
                "<PAGE_END_KEY><![CDATA[]]></PAGE_END_KEY>\n" +
                "<CONV_NO_DATA><![CDATA[]]></CONV_NO_DATA>\n" +
                "<TOTAL_PAGE><![CDATA[1]]></TOTAL_PAGE>\n" +
                "<TOTAL_REC><![CDATA[1]]></TOTAL_REC>\n" +
                "<CURR_TOTAL_PAGE><![CDATA[1]]></CURR_TOTAL_PAGE>\n" +
                "<CURR_TOTAL_REC><![CDATA[1]]></CURR_TOTAL_REC>\n" +
                "<STS_TRACE_ID><![CDATA[1122334455667788990099887766]]></STS_TRACE_ID>\n" +
                "<IttParty_Jrnl_No><![CDATA[112233445566778899]]></IttParty_Jrnl_No>\n" +
                "<SYS_TX_CODE><![CDATA[P1CMSEC05]]></SYS_TX_CODE>\n" +
                "<CHNL_CUST_NO><![CDATA[ZJ1122334455667788#001]]></CHNL_CUST_NO>\n" +
                "</Transaction_Header>\n" +
                "<Transaction_Body>\n" +
                "<response>\n" +
                "<Rvl_Rcrd_Num><![CDATA[1]]></Rvl_Rcrd_Num>\n" +
                "<LIST1 p_type=\"G\">\n" +
                "<StDt><![CDATA[20140601]]></StDt>\n" +
                "<StTm><![CDATA[000000]]></StTm>\n" +
                "<EdTm><![CDATA[235959]]></EdTm>\n" +
                "<Hol_Set_Dt><![CDATA[20140529]]></Hol_Set_Dt>\n" +
                "</LIST1>\n" +
                "</response>\n" +
                "</Transaction_Body>\n" +
                "</Transaction>";
        String resStr = res.replaceAll("\n","").replaceAll("\r","");
        String privateKeyPath = "D:\\data\\cfca\\ccb\\ccb@test_pri.pfx";
        String privateKey = "123456";
        String signture = RsaCodingUtil.encryptByPriPfxFile(resStr,privateKeyPath,privateKey);
        log.debug("{}",signture);
        log.debug("{}",signture.getBytes("UTF-8"));
        byte[] signtureBytes = Base64.encodeBase64(signture.getBytes("UTF-8"));

        String signtureLen = String.valueOf(signtureBytes.length);
        StringBuilder prefixBuffer = new StringBuilder();
        for (int i = 0; i < 10 - signtureLen.length(); i++){
            prefixBuffer.append("0");
        }
        byte[] prefixBytes = prefixBuffer.append(signtureLen).toString().getBytes("UTF-8");
        String resThreeDesStr = ThreeDes.encryptThreeDESECB(resStr,"Bu7KzIWrplmh4nVj0d2Htg==");
        byte[] resThreeBytes = resThreeDesStr.getBytes("UTF-8");
        byte[] resBytes = new byte[10 + signtureBytes.length + resThreeBytes.length];
        System.arraycopy(prefixBytes,0,resBytes,0,10);
        System.arraycopy(signtureBytes,0,resBytes,10,signtureBytes.length);
        System.arraycopy(resThreeBytes,0,resBytes,10+signtureBytes.length,resThreeBytes.length);
        log.info("输出流：{}",new String(resBytes,"UTF-8"));
        response.setHeader("Content-type","text/html;charset=UTF-8");
        response.getOutputStream().write(resBytes);
    }
}
