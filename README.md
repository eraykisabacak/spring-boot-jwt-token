# Spring Boot - Spring Security - JWT Tabanlı Kullanıcı Doğrulama, Yetkilendirme, Register, Login ve RabbitMQ ile Email Gönderme

Bu proje, Spring Boot ve Spring Security kullanılarak geliştirilmiş, JWT (JSON Web Token) tabanlı bir kullanıcı yetkilendirme ve kimlik doğrulama uygulamasını içermektedir. Temel amacı, güvenli bir şekilde kullanıcıların kaydolmalarını, giriş yapmalarını ve belirli rollerle korunan API endpointlerine erişmelerini sağlamaktır.

## Başlangıç

Projeyi başlatmak ve yerel makinenizde çalıştırmak için aşağıdaki adımları takip edin.

### Gereksinimler

Aşağıdaki yazılımların yüklü olduğundan emin olun:
- Docker
- Docker Compose
- Java (min. JDK 18)

### Kullanılan Teknolojiler
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Docker ve Docker Compose
- MySQL

### Kurulum

1. Repository'yi klonlayın:
   ```bash
   git clone https://github.com/eraykisabacak/spring-boot-jwt-token
   ```
2. Proje dizinine gidin:
    ```bash
    cd spring-boot-jwt-token
    ```
3. Spring Boot uygulamasını başlatın:
    ```bash
    ./mvnw spring-boot:run
    ```
4. Docker Compose kullanarak MySQL veri tabanını oluşturun.
    ```bash
    docker-compose up -d
    ```
5. Eğer RabbitMQ ile email göndermek isterseniz application.properties dosyasına kendi gmail email ve password bilgilerinizi yazmanız lazım. (Google gmail ayarlardan bazı izinler vermeniz gerekebilir. )
    ```bash
    spring.mail.username=
    spring.mail.password=
    ```
## Kullanım

Aşağıda proje tarafından sunulan API'lerin kullanımı hakkında bilgiler bulunmaktadır.

### Auth/Register

**Endpoint:** `/auth/register` (POST)

**Açıklama:** Kullanıcı kaydı oluşturur ve RabbitMQ ile welcome emaili gönderilir.

**Parametreler:**
- `username` (zorunlu)
- `password` (zorunlu)
- `name` (zorunlu)
- `authorities` (zorunlu)

### Auth/Login

**Endpoint:** `/auth/login` (POST)

**Açıklama:** Kullanıcı girişi yapar.

**Parametreler:**
- `username` (zorunlu)
- `password` (zorunlu)

### Auth/Welcome

**Endpoint:** `/auth/welcome` (GET)

**Açıklama:** Herkese açık bir hoşgeldiniz sayfasıdır.

### Auth/User

**Endpoint:** `/auth/user` (GET)

**Açıklama:** JWT token ile sadece yetkilendirilmiş kullanıcılar tarafından erişilebilir.

### Auth/Admin

**Endpoint:** `/auth/admin` (GET)

**Açıklama:** JWT token ile sadece admin rolüne sahip kullanıcılar tarafından erişilebilir.

### Auth/Logout

**Endpoint:** `/auth/logout` (GET)

**Açıklama:** JWT token ile kullanıcıyı sistemden çıkarır.

## Katkıda Bulunma

Eğer projeye katkıda bulunmak istiyorsanız, lütfen bir merge request oluşturun.