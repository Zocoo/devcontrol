# devcontrol
http://zpdteck.com:8081/doku.php?id=zpd:doc:spec:menu94

result
{
    "gw_id": "esn",
    "transaction_id": "2423534645",
    "operation": "register",
    "type": "RESPONSE",
    "code": 1,
    "message": "success",
    "valueSet": {}
}

heartbeat
{
    "gw_id": "456123",
    "transaction_id": "2423534645",
    "type": "REQUEST",
    "operation": "heartbeat",
    "valueSet": {
        "sys_load": "50",
        "sys_memfree": "30",
        "sys_uptime": "325",
        "uptime": "232",
        "client_count": "10"
    }
}

register
{
    "transaction_id": "2423534645",
    "gw_id": "2A:C6:8E:B6:67:2F",
    "type": "REQUEST",
    "operation": "register",
    "valueSet": {
        "check_time": "60",
        "gw_mac": "x:x:x:x:x:x",
        "ssid": "zopodo_wifi",
        "gw_address": "192.168.1.1",
        "router_vendor": "ZOPODO",
        "router_type": "ZPD001A",
        "wan_ip": "12.34.131.12",
        "sv": "v1.0.0"
    }
}


accept
restart
{
    "sn": [
        "123789",
        "456123"
    ],
    "operation": "restart"
}

accept
imageUpgrade
{
    "sn": [
        "123789",
        "456123"
    ],
    "operation": "imageUpgrade",
    "version": 2
}


