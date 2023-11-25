# team_louis_password_hasher
 # WELCOME TO TEKTEAM
    Where you can hash and unhash user passwords

# OBJECTIVES

    * The aim of the project to hash and unhash passwords.
NOTE; This project is for practice.

# PROJECT STRUCTURE

hash
|──.mvn
|── src
│          ├── main
│             └── java
│               └── com.tekteam.hash
│                   └──controllers
│                       └── HashController.java
|                    └──entities
|                       └──EncriptMessage.java
│                   └── exceptions
│                       └── AllException.java
|                        └──InternalErrorException
|                     └── payloads
│                       └── ApiResponse.java
|                     └── repositories
│                       └── EncriptRepository.java
│                   └── services
│                       └── HashServiceImplementation.java
│                   └── HashApplication.java
├──resources
├──test
├── build/
├── pom.xml
├── README.md
└── LICENSE

# PROJECT IMPLEMENTATION
 
 # Implementation 1: Encription of password

 public String encrypt(String word) throws Exception {
            SecretKey secretKey = new SecretKeySpec(encryptionKey.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(word.getBytes());
            Base64.getEncoder().encodeToString(encryptedBytes);

            String encryptedValue = String.valueOf(encryptedBytes);

            EncryptMessage encryptedDataEntity = new EncryptMessage();
            encryptedDataEntity.setEncrypted(encryptedValue);
            repository.save(encryptedDataEntity);

            return encryptedValue;
        }

# Implementation 2: Decription of password

        public String decrypt(Long id) throws Exception {
            EncryptMessage encryptedMessage = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Encrypted data not found"));
            byte[] encrypted = encryptedMessage.getEncrypted().getBytes();
            SecretKey secretKey = new SecretKeySpec(encryptionKey.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(decryptedBytes);
        }

# CONTRIBUTION
This project was carried out by a team of three, namely: Louis Idundun, Igwe Onyeka and Ibraheem Adeleye.
