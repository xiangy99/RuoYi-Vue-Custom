package com.ruoyi.common.web.filter;

import cn.hutool.core.io.IoUtil;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * RepeatedlyRequestWrapper 是一个包装类，用于解决 HttpServletRequest 的 InputStream
 * 只能被读取一次的问题。在一些场景下，例如日志记录或者请求参数校验等情况下，需要多次读取请求体内容。然而，HttpServletRequest 的 InputStream
 * 默认只能被读取一次，因此使用该包装类将请求体内容缓存到字节数组中，实现多次读取的目的。
 * <p>
 * 构建可重复读取inputStream的request
 *
 * @author xiangy
 */
public class RepeatedlyRequestWrapper extends HttpServletRequestWrapper {
    
    String UTF8 = "UTF-8";
    
    private final byte[] body;
    
    public RepeatedlyRequestWrapper(HttpServletRequest request, ServletResponse response) throws IOException {
        super(request);
        request.setCharacterEncoding(UTF8);
        response.setCharacterEncoding(UTF8);
        
        body = IoUtil.readBytes(request.getInputStream(), false);
    }
    
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }
            
            @Override
            public int available() throws IOException {
                return body.length;
            }
            
            @Override
            public boolean isFinished() {
                return false;
            }
            
            @Override
            public boolean isReady() {
                return false;
            }
            
            @Override
            public void setReadListener(ReadListener readListener) {
            
            }
        };
    }
}
