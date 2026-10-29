CREATE TABLE IF NOT EXISTS celebrities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    stage_name VARCHAR(150),
    email VARCHAR(150) UNIQUE,
    phone VARCHAR(50),
    category VARCHAR(100),
    instagram_handle VARCHAR(100),
    twitter_handle VARCHAR(100),
    bio TEXT,
    base_fee DECIMAL(15, 2),
    avatar_url VARCHAR(500),
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

