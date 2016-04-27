-- :name list-all-customers :? :*
select * from customer;

-- :name get-customer :? :1
select * from customer where id = :id;
