create table if not exists picture
(
    id          bigserial primary key,
    url         varchar(255) not null,
    name        varchar(255),
    description varchar(255),
    created_at  timestamp,
    likes       bigint check ( likes >= 0 )

);

create table if not exists users
(
    id                 bigserial primary key,
    username           varchar(255) unique not null,
    password           varchar(255)        not null,
    email              varchar(255)        not null,
    first_name         varchar(255),
    last_name          varchar(255),
    profile_picture_id bigint references picture (id),
    role               varchar             not null,
    birth_date         date,
    country            varchar(255),
    city               varchar(255)

);

create table if not exists post
(
    id         bigserial primary key,
    owner_id   bigint references users (id) on UPDATE cascade on delete cascade,
    content    varchar,
    likes      bigint check ( likes >= 0 ),
    created_at timestamp
);


create table if not exists picture_comment
(
    id         bigserial primary key,
    text       varchar(255),
    author_id  bigint references users (id) on update cascade on delete cascade,
    picture_id bigint references picture (id) on update cascade on delete cascade,
    created_at timestamp default current_timestamp
);

create table if not exists post_comment
(
    id         bigserial primary key,
    text       varchar(255),
    author_id  bigint references users (id) on update cascade on delete cascade,
    post_id    bigint references post (id) on update cascade on delete cascade,
    created_at timestamp default current_timestamp
);



create table if not exists chat
(
    id         bigserial primary key,
    user1_id   bigint not null references users (id),
    user2_id   bigint not null references users (id),
    created_at timestamp default current_timestamp,
    unique (user1_id, user2_id),
    CHECK (user1_id < user2_id)
);


create table if not exists message
(
    id           bigserial primary key,
    content      varchar,
    chat_id      bigint references chat (id) on update cascade on delete no action,
    receiver_id  bigint references users (id) on update cascade on delete no action,
    sender_id    bigint references users (id) on update cascade on delete no action,
    sent_at      timestamp default current_timestamp,
    delivered_at timestamp,
    read_at      timestamp,
    status       varchar(20) not null
);

create table if not exists friendship
(
    id         bigserial primary key,
    user_id    bigint references users (id) on update cascade on delete no action,
    friend_id  bigint references users (id) on UPDATE cascade on DELETE no action,
    status     varchar(20) not null,
    created_at timestamp,
    UNIQUE (user_id, friend_id),
    check ( user_id < friend_id )
);


create table if not exists album
(
    id         bigserial primary key,
    album_name varchar(255),
    created_at timestamp,
    owner_id   bigint references users (id) on update cascade on delete no action
);

create table if not exists m2m_album_picture
(
    id         bigserial primary key,
    album_id   bigint references album (id) on update cascade on delete no action,
    picture_id bigint references picture (id) on UPDATE cascade on DELETE no action
)

