CREATE OR REPLACE PACKAGE BODY zip_utility AS
-- -------------------------------------------------------------------------------
-- Name         : ZipUtility
-- Author       : Alekseev Artyom
-- Description  : Zip/unzip data
-- Ammedments   :
--   When         Who             What
--   ===========  ==============  =================================================
--   22-JAN-2019  Alekseev Artyom  Package created
-- --------------------------------------------------------------------------------

function zip(f clob) return blob
as language java
  name 'ZipUtility.zip(oracle.sql.CLOB) return oracle.sql.BLOB';

function unzip(f blob) return blob
as language java
  name 'ZipUtility.unzip(oracle.sql.BLOB) return oracle.sql.BLOB';

END zip_utility;
