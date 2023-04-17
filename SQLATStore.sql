create database ATStoreManagement;
use ATStoreManagement;
use master;
drop database ATStoreManagement;

create table Categories(
	CategoryId			varchar(10)				primary key,
	CategoryLogo		varchar(30)				not null unique
);

create table Roles(
	RoleId				nvarchar(10)			primary key,
	RoleName			varchar(50)				not null
);

create table Accounts(
	Username			varchar(50)				primary key,
	[Password]			varchar(30)				not null,
	UserFullname		varchar(30)				not null,
	UserEmail			varchar(30)				unique not null,
	UserPhone			varchar(10)				not null,
	UserImage			varchar(30)				null
);



create table Authorities(
	AuthorityId			int						primary key identity,
	Username			varchar(50)				foreign key references Accounts(Username),
	RoleId				nvarchar(10)			foreign key references Roles(RoleId)
)
SELECT TOP 8 * FROM Products ORDER BY ProductCreateDate DESC;
create table Products(                                                                           -- delete = update isActive
	ProductId			varchar(30)				primary key,
	ProductName			nvarchar(100)			not null,
	ProductPrice		decimal(10,2)			not null,
	ProductCreateDate	datetime				not null default getDate(),
	ProductDescription	nvarchar(max)			null,       
	ProductImage		varchar(100)			null,
	ProductQuantity		int						null,
	CategoryId			varchar(10)				foreign key references Categories(CategoryId) 
);
select * from Products;
-- đếm số lượng sản phẩm
select count(*) from Products;
-- Search giá từ 0 - 200
select * from Products where (ProductPrice between 0 and 150);

create table Orders(
	OrderId 			bigint					primary key identity,
	Username			varchar(50)				foreign key references Accounts(Username),
	CreatedDate			datetime				not null,
	[Address]			nvarchar(100)			not null
);

create table OrderDetails(
	Id					bigint					primary key identity not null,
	OrderId				bigint					foreign key references Orders(OrderId),
	ProductId			varchar(30)				foreign key references Products(ProductId),
	Price				decimal(10,2)			not null,
	Quantity			int						not null,
);

select * from Products order by ProductCreateDate desc

select * from Orders;

delete from Orders

select * from OrderDetails

select * from Accounts;

select * from Favourites;

create table Favourites(
	Favourited			int						primary key identity,
	Username			varchar(50)				foreign key references Accounts(Username),
	ProductId			varchar(30)				foreign key references Products(ProductId),
	ViewedDate			datetime				not null default getDate(),
	IsLiked				bit						not null default 0,
	LikedDate			datetime				null
);

update Favourites
set IsLiked = 1
where Favourited = 7

delete from Favourites where Username = 'binhan' and ProductId = 'FD0821-100';

select * from Favourites where Username = 'binhan' and ProductId = 'FD0821-100';

select * from Favourites where ProductId = '553558-215' and Username = 'binhan'


select * from Accounts;
select * from Favourites;

-- Hiển thị những sản phẩm mà người dùng đã thích
select * from Products p inner join Favourites f on p.ProductId = f.ProductId where Username = 'binhan' and IsLiked = 1;


delete from Favourites where Username = 'binhan' and ProductId = 'DC0774-501';



-----------------------------------------------------------------------------------------------------------
-- insert Category
select * from Categories;

insert into Categories (CategoryId, CategoryLogo) values ('Nike','nike.png');
insert into Categories (CategoryId, CategoryLogo) values ('Running','running.png');
insert into Categories (CategoryId, CategoryLogo) values ('Jordan', 'jordan.png');
insert into Categories (CategoryId, CategoryLogo) values ('Training', 'training.png');
-----------------------------------------------------------------------------------------------------------
-- insert Product
select * from Products
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('FD0821-100', 'Nike Air Max 90 Futura', 160, 'We have reimagined an icon of Air with bursts of high-energy colors and layered leather overlays to bring you skillfully crafted luxury.', 'air-max-90-futura-womens-shoes-kvRZ4h.png', 10, 'Nike');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('CW2288-111', 'Nike Air Force 1’07', 110, 'The radiance lives on in the Nike Air Force 1 ’07, the b-ball OG that puts a fresh spin on what you know best', 'air-force-1-07-mens-shoes-5QFp5Z.png', 10, 'Nike');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DM0573-101', 'Nike Air Force 1 React', 140, 'From hoops staple to urban street legend, the Nike AF1 React takes another step forward into shoe iconography.', 'air-force-1-react-mens-shoes-WSjfs8.png', 10, 'Nike');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DX1156-100', 'Nike Air Force 1 Low Retro QS', 150, 'Can an icon get more iconic? This AF1 goes for it with an anniversary edition of the 2002 design honoring the cultures of the West Indies.', 'air-force-1-low-retro-qs-mens-shoes-XZG2zG.png', 10, 'Nike');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('AV2187-140', 'Air Jordan 11 Retro Low', 185, 'Let is cut to the chase—the AJ11 is all-time. MJ won 72 games and a title while wearing em.', 'air-jordan-11-retro-low-mens-shoes-4kj41D.png', 10, 'Nike');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DR0453-001', 'Nike Air Max Pulse', 150, 'Keeping it real, the Air Max Pulse pulls inspiration from the London music scene, bringing an underground touch to the iconic Air Max line.', 'air-max-pulse-mens-shoes-ShS3tL.png', 10, 'Nike');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DM0029-104', 'Nike Air Max 90', 130, 'Lace up and feel the legacy. Produced at the intersection of art, music and culture, this champion running shoe helped define the ‘90s.', 'air-max-90-mens-shoes-DLDWPT.png', 10, 'Nike');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DM0029-104', 'Nike Air Max 90', 130, 'Lace up and feel the legacy. Produced at the intersection of art, music and culture, this champion running shoe helped define the ‘90s.', 'air-max-90-mens-shoes-DLDWPT.png', 10, 'Nike');

-- Jordan
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('553558-215', 'Air Jordan 1 Low', 110, 'Inspired by the original that debuted in 1985, the Air Jordan 1 Low offers a clean, classic look that is familiar yet always fresh.', 'air-jordan-1-low-mens-shoes-0LXhbn.png', 10, 'Jordan');

insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DD9315-104', 'Air Jordan 1 Low G', 140, 'Feel unbeatable, from the tee box to the final putt. Inspired by one of the most iconic sneakers of all time, the Air Jordan 1 G is an instant classic on the course.', 'air-jordan-1-low-g-golf-shoes-jChrQ3.png', 10, 'Jordan');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DC0774-501', 'Air Jordan 1 Low', 110, 'Always fresh, always in-season. The AJ1 is a wearable piece of Jordan history, with premium materials and a classic silhouette.', 'air-jordan-1-low-womens-shoes-rJrHLw.png', 10, 'Jordan');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('CT8529-100', 'Air Jordan 6 Retro', 200, 'Be cool. Stay cool. The AJ6 "Cool Grey" lets your style take flight with a colorway rooted to Jordan Brand DNA.', 'air-jordan-6-retro-mens-shoes-CVPFVM.png', 10, 'Jordan');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('CU9307-106', 'Air Jordan 7 Retro', 210, 'Inspired by the shoe originally worn by MJ during the 92 season and summer of basketball, the Air Jordan 7 Retro revives its championship legacy for a new generation of sneakerheads.', 'air-jordan-7-retro-mens-shoes-098sXt.png', 10, 'Jordan');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('CQ3835-517', 'Air Jordan 1 Hi FlyEase', 150, 'The Air Jordan 1 Hi FlyEase combines the coveted style of Michael Jordan is first signature sneaker with a quick and easy one-handed entry system.', 'air-jordan-1-hi-flyease-mens-shoes-VjGcGX.png', 10, 'Jordan');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DV1335-800', 'Air Jordan 1 Low SE', 95, 'New colors and textures combine to give the AJ1 a fresh remake, but you are still getting the classic sneaker with its familiar feel.', 'air-jordan-1-low-se-big-kids-shoes-CZ6xPG.png', 10, 'Jordan');


-- Running
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DA7245-007', 'Nike Vomero 16', 150, 'The Vomero 16 adds responsive ZoomX foam cushioning, bringing an energetic pop to your stride that’s perfect for high mileage on the road.', 'vomero-16-mens-road-running-shoes-8zSBBk.png', 10, 'Running');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('FD0787-300', 'Nike Winflo 9', 100, 'Invite a sense of calm to join you on your route with these road runners.', 'wio-9-mens-road-running-shoes-zLBLt0.png', 10, 'Running');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('CT0079-101', 'Nike Air Zoom LJ Elite', 150, 'We geared the ultra-lightweight long jumping shoes to reach new heights.', 'air-zoom-lj-elite-track-field-jumping-spikes-pSDffB.png', 10, 'Running');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('942836-100', 'Nike Free Run 2018', 100, 'The Nike Free Run 2018 Shoe has been updated to deliver an even more adaptive fit than before.', 'free-run-2018-mens-road-running-shoes-4VTnGqeZ.png', 10, 'Running');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DJ6158-003', 'Nike Pegasus Trail 4', 140, 'Running is your daily ritual, taking you from road to trail as you seek out new adventures and goals.', 'pegasus-trail-4-mens-trail-running-shoes-Fqc7qj.png', 10, 'Running');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('685134-102', 'Nike Zoom Rival SD 2', 65, 'The Nike Zoom Rival SD 2 delivers a flexible fit that is ready for your next competition.', 'zoom-rival-sd-2-track-field-throwing-shoes-oLb8zO.png', 10, 'Running');

-- Training
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DJ4487-121', 'Nike Romaleos 4 SE', 200, 'Intensely strong and stable, the Nike Romaleos 4 SE features a wide, flat base and a supportive foundation for your toughest lifts.', 'romaleos-4-se-weightlifting-shoe-r7zff9.png', 10, 'Training');
insert into Products (ProductId, ProductName, ProductPrice, ProductDescription, ProductImage, ProductQuantity, CategoryId) values 
('DH3382-504', 'Nike Metcon 7 AMP', 140, 'Pay homage to a hero, an inspiration, a guiding light who helped you along the way in this special Nike Metcon 7 AMP.', 'metcon-7-amp-training-shoes-gJT8QZ.png', 10, 'Training');



-----------------------------------------------------------------------------------------------------------
-- insert Roles
insert into Roles (RoleId, RoleName) values (N'CUST', N'Customers');
insert into Roles (RoleId, RoleName) values (N'DIRE', N'Directors');
insert into Roles (RoleId, RoleName) values (N'STAF', N'Staffs');

-- admin: director & staff
insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values
('binhan', '123', 'Lai Binh An', 'an.chantroimoi@gmail.com', '0900000001', 'an.png');
insert into Authorities (Username, RoleId) values ('binhan', N'DIRE');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values
('duyan', '123', 'Nguyen Duy An', 'Nhuy50528@gmail.com', '0900000002', 'duyan.png');
insert into Authorities (Username, RoleId) values ('duyan', N'DIRE');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values
('dungtrung', '123', 'Lam Dung Trung', 'ldt1122001@gmail.com', '0900000003', 'trung.png');
insert into Authorities (Username, RoleId) values ('dungtrung', N'DIRE');

-- admin: staff
insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('tommy', '123', 'Tommy Shelby', 'tommy@gmail.com', '0900000000', 'user.png');
insert into Authorities (Username, RoleId) values ('tommy', N'STAF');

-- customer
insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('grace', '123', 'Grace Burgess', 'grace@gmail.com', '0900000004', 'grace.png');
insert into Authorities (Username, RoleId) values ('grace', N'CUST');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('arthur', '123', 'Arthur Shelby', 'arthur@gmail.com', '0900000005', 'arthur.png');
insert into Authorities (Username, RoleId) values ('arthur', N'CUST');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('john', '123', 'John Shelby', 'john@gmail.com', '0900000006', 'john.png');
insert into Authorities (Username, RoleId) values ('john', N'CUST');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('finn', '123', 'Finn Shelby', 'finn@gmail.com', '0900000007', 'finn.png');
insert into Authorities (Username, RoleId) values ('finn', N'CUST');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('ada', '123', 'Ada Shelby', 'ada@gmail.com', '0900000008', 'ada.png');
insert into Authorities (Username, RoleId) values ('ada', N'CUST');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('lizzie', '123', 'Lizzie Starke', 'lizzie@gmail.com', '0900000009', 'lizzie.png');
insert into Authorities (Username, RoleId) values ('lizzie', N'CUST');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('aberama', '123', 'Aberama Gold', 'aberama@gmail.com', '0900000010', 'aberama.png');
insert into Authorities (Username, RoleId) values ('aberama', N'CUST');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('solomons', '123', 'Alfie Solomons', 'solomons@gmail.com', '0900000007', 'solomons.png');
insert into Authorities (Username, RoleId) values ('solomons', N'CUST');

insert into Accounts (Username, [Password], UserFullname, UserEmail, UserPhone, UserImage) values 
('text', '123', 'Text', 'text@gmail.com', '0900000011', 'text.png');
insert into Authorities (Username, RoleId) values ('text', N'CUST');


-- Thống kê sản phẩm được bao nhiêu lượt like
select * from Favourites;
select * from Favourites where Username = 'binhan' and IsLiked = 1;
select p.CategoryId , p.ProductId, p.ProductName, sum(cast(f.IsLiked as int)) as [Total like] from Favourites f inner join Products p on f.ProductId = p.ProductId
group by p.CategoryId, p.ProductId, p.ProductName, f.IsLiked

-- Hiển thị thông tin những người đã like sp theo tên sản phẩm
select ac.UserFullname, ac.UserEmail from Accounts ac inner join Favourites f on ac.Username = f.Username
                                                      inner join Products p on f.ProductId = p.ProductId
													  where p.ProductId = 'CW2288-111' and f.IsLiked = 1;

-- Đếm sản phẩm được bao nhiêu lượt thích
select  ProductId, count(IsLiked) as [Total like] from Favourites
group by ProductId

-- Quản lý kho hàng: Thống kê các sản phẩm và số lượng



-- Ghi nhận đơn hàng trong ngày
-- khách hàng, địa chỉ giao, sản phẩm, số lượng, giá, ngày đặt
select * from Orders

select * from OrderDetails

select * from Orders where CreatedDate = '2023-04-11';