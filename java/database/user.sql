-- ********************************************************************************
-- This script creates the database users and grants them the necessary permissions
-- ********************************************************************************

CREATE USER pokemon_final_owner
WITH PASSWORD 'shaazam';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO pokemon_final_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO pokemon_final_owner;

CREATE USER pokemon_final_appuser
WITH PASSWORD 'pikapika';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO pokemon_final_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO pokemon_final_appuser;
