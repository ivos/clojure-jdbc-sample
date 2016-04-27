-- name: list-all-customers
select * from customer;

-- name: get-customer
select * from customer where id = :id;
