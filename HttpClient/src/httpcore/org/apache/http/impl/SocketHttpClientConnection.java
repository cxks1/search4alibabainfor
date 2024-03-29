/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import org.apache.http.HttpInetConnection;
import org.apache.http.impl.io.SocketInputBuffer;
import org.apache.http.impl.io.SocketOutputBuffer;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * Implementation of a client-side HTTP connection that can be bound to an
 * arbitrary {@link Socket} for receiving data from and transmitting data to
 * a remote server.
 * <p>
 * The following parameters can be used to customize the behavior of this
 * class:
 * <ul>
 *  <li>{@link org.apache.http.params.CoreProtocolPNames#STRICT_TRANSFER_ENCODING}</li>
 *  <li>{@link org.apache.http.params.CoreProtocolPNames#HTTP_ELEMENT_CHARSET}</li>
 *  <li>{@link org.apache.http.params.CoreConnectionPNames#SOCKET_BUFFER_SIZE}</li>
 *  <li>{@link org.apache.http.params.CoreConnectionPNames#MAX_LINE_LENGTH}</li>
 *  <li>{@link org.apache.http.params.CoreConnectionPNames#MAX_HEADER_COUNT}</li>
 * </ul>
 *
 * @since 4.0
 */
public class SocketHttpClientConnection
        extends AbstractHttpClientConnection implements HttpInetConnection {

    private volatile boolean open;
    private volatile Socket socket = null;

    public SocketHttpClientConnection() {
        super();
    }

    protected void assertNotOpen() {
        if (this.open) {
            throw new IllegalStateException("Connection is already open");
        }
    }

    protected void assertOpen() {
        if (!this.open) {
            throw new IllegalStateException("Connection is not open");
        }
    }

    /**
     * Creates an instance of {@link SocketInputBuffer} to be used for
     * receiving data from the given {@link Socket}.
     * <p>
     * This method can be overridden in a super class in order to provide
     * a custom implementation of {@link SessionInputBuffer} interface.
     *
     * @see SocketInputBuffer#SocketInputBuffer(Socket, int, HttpParams)
     *
     * @param socket the socket.
     * @param buffersize the buffer size.
     * @param params HTTP parameters.
     * @return session input buffer.
     * @throws IOException in case of an I/O error.
     */
    protected SessionInputBuffer createSessionInputBuffer(
            final Socket socket,
            int buffersize,
            final HttpParams params) throws IOException {
        return new SocketInputBuffer(socket, buffersize, params);
    }

    /**
     * Creates an instance of {@link SessionOutputBuffer} to be used for
     * sending data to the given {@link Socket}.
     * <p>
     * This method can be overridden in a super class in order to provide
     * a custom implementation of {@link SocketOutputBuffer} interface.
     *
     * @see SocketOutputBuffer#SocketOutputBuffer(Socket, int, HttpParams)
     *
     * @param socket the socket.
     * @param buffersize the buffer size.
     * @param params HTTP parameters.
     * @return session output buffer.
     * @throws IOException in case of an I/O error.
     */
    protected SessionOutputBuffer createSessionOutputBuffer(
            final Socket socket,
            int buffersize,
            final HttpParams params) throws IOException {
        return new SocketOutputBuffer(socket, buffersize, params);
    }

    /**
     * Binds this connection to the given {@link Socket}. This socket will be
     * used by the connection to send and receive data.
     * <p>
     * This method will invoke {@link #createSessionInputBuffer(Socket, int, HttpParams)}
     * and {@link #createSessionOutputBuffer(Socket, int, HttpParams)} methods
     * to create session input / output buffers bound to this socket and then
     * will invoke {@link #init(SessionInputBuffer, SessionOutputBuffer, HttpParams)}
     * method to pass references to those buffers to the underlying HTTP message
     * parser and formatter.
     * <p>
     * After this method's execution the connection status will be reported
     * as open and the {@link #isOpen()} will return <code>true</code>.
     *
     * @param socket the socket.
     * @param params HTTP parameters.
     * @throws IOException in case of an I/O error.
     */
    protected void bind(
            final Socket socket,
            final HttpParams params) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("Socket may not be null");
        }
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        this.socket = socket;

        int buffersize = HttpConnectionParams.getSocketBufferSize(params);

        init(
                createSessionInputBuffer(socket, buffersize, params),
                createSessionOutputBuffer(socket, buffersize, params),
                params);

        this.open = true;
    }

    public boolean isOpen() {
        return this.open;
    }

    protected Socket getSocket() {
        return this.socket;
    }

    public InetAddress getLocalAddress() {
        if (this.socket != null) {
            return this.socket.getLocalAddress();
        } else {
            return null;
        }
    }

    public int getLocalPort() {
        if (this.socket != null) {
            return this.socket.getLocalPort();
        } else {
            return -1;
        }
    }

    public InetAddress getRemoteAddress() {
        if (this.socket != null) {
            return this.socket.getInetAddress();
        } else {
            return null;
        }
    }

    public int getRemotePort() {
        if (this.socket != null) {
            return this.socket.getPort();
        } else {
            return -1;
        }
    }

    public void setSocketTimeout(int timeout) {
        assertOpen();
        if (this.socket != null) {
            try {
                this.socket.setSoTimeout(timeout);
            } catch (SocketException ignore) {
                // It is not quite clear from the Sun's documentation if there are any
                // other legitimate cases for a socket exception to be thrown when setting
                // SO_TIMEOUT besides the socket being already closed
            }
        }
    }

    public int getSocketTimeout() {
        if (this.socket != null) {
            try {
                return this.socket.getSoTimeout();
            } catch (SocketException ignore) {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public void shutdown() throws IOException {
        this.open = false;
        Socket tmpsocket = this.socket;
        if (tmpsocket != null) {
            tmpsocket.close();
        }
    }

    public void close() throws IOException {
        if (!this.open) {
            return;
        }
        this.open = false;
        Socket sock = this.socket;
        try {
            doFlush();
            try {
                try {
                    sock.shutdownOutput();
                } catch (IOException ignore) {
                }
                try {
                    sock.shutdownInput();
                } catch (IOException ignore) {
                }
            } catch (UnsupportedOperationException ignore) {
                // if one isn't supported, the other one isn't either
            }
        } finally {
            sock.close();
        }
    }

}
