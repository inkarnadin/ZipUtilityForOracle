# ZipUtilityForOracle
Small package of zip/unzip function which used JVM in Oracle stored procedure

Example:
1. Create test table and insert data:
```sql
create table test_table (id number, attachment clob);
insert into test_table (id, attachment) values (1, 'It is a just test data: Save me! I am hungry russian developer... I dream to leave this place.');
```

2. Use package functions:
```sql
select t.attachment,
       zip_utility.zip(t.attachment),
       zip_utility.unzip(zip_utility.zip(t.attachment))
  from test_table t
```  
