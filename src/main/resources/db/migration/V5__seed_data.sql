INSERT INTO users (id, email, display_name, password_hash, role, created_at, updated_at)
VALUES (1, 'admin@taskflow.local', 'Admin User', '123', 'MEMBER', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

INSERT INTO projects (id, name, description, status, owner_id, created_at, updated_at)
VALUES (1, 'Demo Project', 'Проект для тестов API', 'ACTIVE', 1, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;