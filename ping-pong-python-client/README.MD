1. Install libraries for Python:

```bash
pip install grpcio
```

```bash
pip install grpcio-tools
```

```bash
pip install flask
```

2. Generate the gRPC client stubs from the `ping-pong.proto` file:

```bash
python -m grpc_tools.protoc -I. --python_out=. --grpc_python_out=. ping-pong.proto
```

3. Run the gRPC server:

```bash
python ping-pong-python-client.py
```

4. Trigger endpoint:

```http
curl http://localhost:5000/ping
```