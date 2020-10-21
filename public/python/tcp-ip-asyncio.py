#!/usr/bin/python3.6


import sys


"""
Servidor tcp/ip
"""
if sys.argv[1] == '1':

    import asyncio

    async def handle_echo(reader, writer):
        data = await reader.read(100)
        message = data.decode()
        addr = writer.get_extra_info('peername')
        print("Received %r from %r" % (message, addr))

        print("Send: %r" % message)
        writer.write(data)
        await writer.drain()

        print("Close the client socket")
        writer.close()

    loop = asyncio.get_event_loop()
    coro = asyncio.start_server(handle_echo, '127.0.0.1', 8888, loop=loop)
    server = loop.run_until_complete(coro)

    # Serve requests until Ctrl+C is pressed
    print('Serving on {}'.format(server.sockets[0].getsockname()))
    try:
        loop.run_forever()
    except KeyboardInterrupt:
        pass

    # Close the server
    server.close()
    loop.run_until_complete(server.wait_closed())
    loop.close()

"""
Cliente tcp/ip
"""
if sys.argv[1] == '2':

    import asyncio

    async def tcp_echo_client(message, loop):
        reader, writer = await asyncio.open_connection('127.0.0.1', 8888,
                                                    loop=loop)

        print('Send: %r' % message)
        writer.write(message.encode())

        data = await reader.read(100)
        print('Received: %r' % data.decode())

        print('Close the socket')
        writer.close()

    message = 'Hello World!'
    loop = asyncio.get_event_loop()
    loop.run_until_complete(tcp_echo_client(message, loop))
    loop.close()