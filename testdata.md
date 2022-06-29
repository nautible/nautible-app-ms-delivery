# 動作確認用テストデータ

## データ作成
- curl

```
curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d "{\"deliveryPlanDate\":\"2021-01-01\", \"deliveryPrice\":\"1000\", \"deliveryName\":\"hoge\", \"deliveryPostCode\": \"123-4567\", \"deliveryAddress1\": \"tokyo\", \"deliveryAddress2\": \"W100\", \"deliveryTel\": \"03-1111-2222\" }"  http://localhost:8080/delivery
```


## データ更新

- curl

```
curl -H "Accept: application/json" -H "Content-type: application/json" -X PUT -d "{\"deliveryNo\":\"D0000000001\", \"deliveryPlanDate\":\"2021-01-01\", \"deliveryPrice\":\"1000\", \"deliveryName\":\"hoge\", \"deliveryPostCode\": \"123-4567\", \"deliveryAddress1\": \"tokyo\", \"deliveryAddress2\": \"W100\", \"deliveryTel\": \"03-1111-2222\", \"deliveryStatus\":\"SHIPMENT_COMPLETE\" }"  http://localhost:8080/delivery
```

## ID指定データ取得

- curl

```
curl -H "Accept: application/json" -X GET localhost:8080/delivery/D0000000001
```

## データ削除

- curl

```
curl -H "Accept: application/json" -X DELETE localhost:8080/delivery/D0000000001
```


## Dynamodb（localstack)のデータ確認

- 事前にプロファイルを作成する「/.aws/config」

```
[profile localstack]
region = ap-northeast-1
output = json
```

- 事前にプロファイルを作成する「/.aws/credentials」

```
[localstack]
aws_access_key_id = test-key
aws_secret_access_key = test-secret
```

- Delivery テーブルの取得

```
aws dynamodb scan --table-name=Delivery --profile localstack --endpoint-url=http://localhost:4566
```

- CronJob の確認

    - job の定義確認
    
    ```
    kubectl get cronjob delivery-cronjob -n nautible-app-ms
    ```

    - job の実行状況確認

    ```
    kubectl get jobs --watch -n nautible-app-ms
    ```



  