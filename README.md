# Rate-Limiter-based-on-Ip-Address-using-Bucket4j-in-spring-boot-application
Follow following steps to validate functionality: (Application Used for Validation is Apache Jmeter.)

Step 1: Create group thread as screenshot below as reference.
![ratelimiting 1](https://github.com/Vinay-Bafna/Rate-Limiter-based-on-Ip-Address-using-Bucket4j-in-spring-boot-application/assets/55124298/08319882-0167-4256-9f19-bb3b4da21b2a)

Step 2: Create http request along with thread as screenshot below as reference and add details like Port Number, Server Name, Path etc.
![ratelimiting 2](https://github.com/Vinay-Bafna/Rate-Limiter-based-on-Ip-Address-using-Bucket4j-in-spring-boot-application/assets/55124298/89ec6dc8-48bb-47ec-be30-8c4791ab8d5b)

Step 3: As per request limit in 1 second maximum 5 request allowed futher request was rejected for perticular client address.
![ratelimiting 3](https://github.com/Vinay-Bafna/Rate-Limiter-based-on-Ip-Address-using-Bucket4j-in-spring-boot-application/assets/55124298/03874d70-6876-4061-bc1f-08f9fed2c8bf)

![ratelimiting 4](https://github.com/Vinay-Bafna/Rate-Limiter-based-on-Ip-Address-using-Bucket4j-in-spring-boot-application/assets/55124298/3e35802d-da2e-4a96-955a-3889ba7eef92)


Step 4: if client try to exceed limit request will auto denied for time duration block.
![ratelimiting 5](https://github.com/Vinay-Bafna/Rate-Limiter-based-on-Ip-Address-using-Bucket4j-in-spring-boot-application/assets/55124298/0217fb5d-c0fe-454f-9af3-a73dfc9db896)

Thanks For Reading!
