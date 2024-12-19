# Not Defteri Uygulaması

Bu proje, Java ile yazılmış basit bir **Not Defteri** uygulamasıdır. Kullanıcıların notlar oluşturmasına, notları görüntülemesine, güncellemesine ve silmesine olanak tanır. Uygulama ayrıca notları veritabanında saklar ve kullanıcıların giriş yaparak notlarını yönetmelerine imkan sağlar.

## Özellikler

- Kullanıcı girişi ve kaydı
- Not ekleme, silme ve güncelleme işlemleri
- Notları sıralama (Tarihe göre veya Önem derecesine göre)
- Notları veritabanına kaydetme
- Farklı not türleri (Örneğin: Basit, Önemli)
- Veritabanı bağlantısı (MySQL)
- Uygulama, yazılım tasarımını esnek, sürdürülebilir ve genişletilebilir hale getirmek için aşağıdaki tasarım desenlerini kullanır:

1. Singleton
VeritabaniBaglantisi sınıfı, veritabanı bağlantısının sadece bir örneğinin oluşturulmasını sağlar ve her yerden erişim için tek bir örnek kullanır.
2. Factory
NotSiralamaFactory sınıfı, farklı sıralama stratejilerine göre uygun NotSiralamaStratejisi nesnelerini oluşturur. Bu sayede sıralama işlemi esnek bir şekilde yönetilir.
3. Observer
NotDefteri sınıfı, notlardaki değişiklikleri kullanıcı arayüzüne (UI) otomatik olarak bildirir. Bu desen, notlar eklenip silindikçe UI'nin güncellenmesini sağlar.
4. Decorator
OnemliNot sınıfı, mevcut Not nesnelerine yeni işlevsellik eklemek için Decorator desenini kullanır. Böylece notlara dinamik olarak yeni özellikler eklenebilir (örneğin, "Önemli" etiketi eklemek).
5. Strategy
NotSiralamaYoneticisi, sıralama işlemlerini farklı stratejilerle yönetir. TariheGoreSiralama ve OnemDerecesineGoreSiralama gibi farklı stratejiler, notların sıralanmasını özelleştirir.
![Ekran Görüntüsü (215)](https://github.com/user-attachments/assets/9f9d9ce1-4baf-4e48-9736-df2e7711cf66)
![Ekran Görüntüsü (216)](https://github.com/user-attachments/assets/b69bd1f2-e394-44c1-a101-0cc5f090d3b3)

