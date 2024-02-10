import grpc
import ping_pong_pb2
import ping_pong_pb2_grpc
from flask import Flask, request, jsonify

app = Flask(__name__)


class PingPongClient:
    def __init__(self, server_address, server_port):
        self.server_address = server_address
        self.server_port = server_port

        self.channel = grpc.insecure_channel(f'{self.server_address}:{self.server_port}')

        self.stub = ping_pong_pb2_grpc.PingPongServiceStub(self.channel)

    def ping(self, message):

        ping_request = ping_pong_pb2.Ping(message=message)

        return self.stub.PingPong(ping_request).message


client = PingPongClient('127.0.0.1', 8080)


@app.route('/ping', methods=['GET'])
def ping():

    return client.ping('Python Ping')


if __name__ == '__main__':

    app.run(host='0.0.0.0', port=5000)
