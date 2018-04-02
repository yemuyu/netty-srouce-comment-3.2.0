/*
 * Copyright 2009 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.jboss.netty.handler.codec.http;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * An HTTP message which provides common properties for {@link HttpRequest} and
 * {@link HttpResponse}.
 *
 * @author <a href="http://www.jboss.org/netty/">The Netty Project</a>
 * @author Andy Taylor (andy.taylor@jboss.org)
 * @author <a href="http://gleamynode.net/">Trustin Lee</a>
 * @version $Rev$, $Date$
 *
 * @see HttpHeaders
 *
 * @apiviz.landmark
 * @apiviz.has org.jboss.netty.handler.codec.http.HttpChunk oneway - - is followed by
 */
public interface HttpMessage {

    /**
     * Returns the header value with the specified header name.  If there are
     * more than one header value for the specified header name, the first
     * value is returned.
     *
     * @return the header value or {@code null} if there is no such header
     *
     */
    String getHeader(String name);

    /**
     * Returns the header values with the specified header name.
     *
     * @return the {@link List} of header values.  An empty list if there is no
     *         such header.
     */
    List<String> getHeaders(String name);

    /**
     * Returns the all header names and values that this message contains.
     *
     * @return the {@link List} of the header name-value pairs.  An empty list
     *         if there is no header in this message.
     */
    List<Map.Entry<String, String>> getHeaders();

    /**
     * Returns {@code true} if and only if there is a header with the specified
     * header name.
     */
    boolean containsHeader(String name);

    /**
     * Returns the {@link Set} of all header names that this message contains.
     */
    Set<String> getHeaderNames();

    /**
     * Returns the protocol version of this message.
     */
    HttpVersion getProtocolVersion();

    /**
     * Sets the protocol version of this message.
     */
    void setProtocolVersion(HttpVersion version);

    /**
     * Returns the content of this message.  If there is no content or
     * {@link #isChunked()} returns {@code true}, an
     * {@link ChannelBuffers#EMPTY_BUFFER} is returned.
     */
    ChannelBuffer getContent();

    /**
     * Sets the content of this message.  If {@code null} is specified,
     * the content of this message will be set to {@link ChannelBuffers#EMPTY_BUFFER}.
     */
    void setContent(ChannelBuffer content);

    /**
     * Adds a new header with the specified name and value.
     */
    void addHeader(String name, Object value);

    /**
     * Sets a new header with the specified name and value.  If there is an
     * existing header with the same name, the existing header is removed.
     */
    void setHeader(String name, Object value);

    /**
     * Sets a new header with the specified name and values.  If there is an
     * existing header with the same name, the existing header is removed.
     */
    void setHeader(String name, Iterable<?> values);

    /**
     * Removes the header with the specified name.
     */
    void removeHeader(String name);

    /**
     * Removes all headers from this message.
     */
    void clearHeaders();

    /**
     * @deprecated Use {@link HttpHeaders#getContentLength(HttpMessage)} instead.
     */
    @Deprecated
    long getContentLength();

    /**
     * @deprecated Use {@link HttpHeaders#getContentLength(HttpMessage, long)} instead.
     */
    @Deprecated
    long getContentLength(long defaultValue);

    /**
     * Returns {@code true} if and only if this message does not have any
     * content but the {@link HttpChunk}s, which is generated by
     * {@link HttpMessageDecoder} consecutively, contain the actual content.
     * <p>
     * Please note that this method will keep returning {@code true} if the
     * {@code "Transfer-Encoding"} of this message is {@code "chunked"}, even if
     * you attempt to override this property by calling {@link #setChunked(boolean)}
     * with {@code false}.
     */
    boolean isChunked();

    /**
     * Sets if this message does not have any content but the
     * {@link HttpChunk}s, which is generated by {@link HttpMessageDecoder}
     * consecutively, contain the actual content.
     * <p>
     * If this method is called with {@code true}, the content of this message
     * becomes {@link ChannelBuffers#EMPTY_BUFFER}.
     * <p>
     * Even if this method is called with {@code false}, {@link #isChunked()}
     * will keep returning {@code true} if the {@code "Transfer-Encoding"} of
     * this message is {@code "chunked"}.
     */
    void setChunked(boolean chunked);

    /**
     * @deprecated Use {@link HttpHeaders#isKeepAlive(HttpMessage)} instead.
     */
    @Deprecated
    boolean isKeepAlive();
}