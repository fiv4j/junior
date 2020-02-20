-- В системе заданы таблицы:
-- product(id, name, type_id, expired_date, price)
-- type(id, name)

-- 1. Написать запрос получение всех продуктов с типом "СЫР".
select p.*
from product p
    inner join type t
        on t.id = p.type_id
where t.name = 'СЫР';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное".
select *
from product
where name like '%мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select *
from product
where expired_date between date_trunc('month', (current_date + interval '1 month'))
                       and date_trunc('month', (current_date + interval '2 month'));

-- 4. Написать запрос, который выводит самый дорогой продукт.
select *
from product
where price = (select max(price)
               from product);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(p.id)
from product p
    inner join type t
        on t.id = p.type_id
group by t.id;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
select p.*
from product p
    inner join type t
        on t.id = p.type_id
where t.name in ('СЫР', 'МОЛОКО');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
select t.name, count(p.id)
from product p
    inner join type t
        on t.id = p.type_id
group by t.id
having count(p.id) < 10;

-- 8. Вывести все продукты и их тип.
select p.name, t.name, p.expired_date, p.price
from product p
    inner join type t
        on t.id = p.type_id;