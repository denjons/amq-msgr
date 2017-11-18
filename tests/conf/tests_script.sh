curl -d '{"userId":"4tv3443c43-c35v34v533-c353434vv", "request":"test request", "tags": ["test-tag"]}' -H "Content-Type: application/json" -X POST http://10.0.3.20:8080/add

echo "Benchmarking 100 requests from single client"

ab -p testfile.json -n 100 -T application/json 10.0.3.20:8080/add
