echo "running cli script"

sleep 10

echo "sending script:"
echo $(cat replSetConfig.js)

mongo 10.0.3.30:27017 /replSetConfig.js
