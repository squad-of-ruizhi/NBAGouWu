package com.yc.gw.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
public class SendSms {
	public String send(String phoneNumber, String templateParam) {
		CommonResponse response = null;
		
		// AccessKey鐨刬d涓庡瘑鐮�
		DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIj4r54b67W3az", "t6GnvlqTZ4kbaj7L3skx4yyOXF2rpn");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("RegionId", "default");
        request.putQueryParameter("PhoneNumbers", phoneNumber); // 鎵嬫満鍙�
        request.putQueryParameter("SignName", "GouWu动态web工程");
        request.putQueryParameter("TemplateCode", "SMS_171853086");
        request.putQueryParameter("TemplateParam", templateParam); // 楠岃瘉鐮�
        try {
            response = client.getCommonResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return response.getData();
	}
}