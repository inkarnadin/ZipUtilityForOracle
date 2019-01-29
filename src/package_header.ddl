CREATE OR REPLACE PACKAGE zip_utility AS
-- -------------------------------------------------------------------------------
-- Name         : ZipUtility
-- Author       : Alekseev Artyom
-- Description  : Zip/unzip data
-- Ammedments   :
--   When         Who             What
--   ===========  ==============  =================================================
--   22-JAN-2019  Alekseev Artyom  Package created
-- --------------------------------------------------------------------------------

function zip(f clob) return blob;
function unzip(f blob) return blob;

END zip_utility;
