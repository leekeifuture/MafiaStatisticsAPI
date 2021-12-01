alter table if exists numbers_statistics_all drop constraint if exists FKpidpxqnpx4ujj9lu0fq0ogu2n;

alter table if exists numbers_statistics_all drop constraint if exists FKq0leo2bb7wlkc70d1j9f5wx2i;

alter table if exists numbers_statistics_all drop constraint if exists FKmxru3yfsatkxfj7k12uhd285n;

alter table if exists numbers_statistics_all drop constraint if exists FKtb3ofl100ba69kulg6sy80qdy;

alter table if exists numbers_statistics_all drop constraint if exists FKe61qp8cr5i4mc08is9mbw84rc;

alter table if exists numbers_statistics_all drop constraint if exists FKprmoxele31xu2130iwfjttd25;

alter table if exists numbers_statistics_all drop constraint if exists FKbg1wixuh3441cdhs8yhid9j36;

alter table if exists numbers_statistics_all drop constraint if exists FK9lqmu1bodeb1ws253iw9wbduh;

alter table if exists numbers_statistics_all drop constraint if exists FKppc2u90ciopcda9ir108qbkno;

alter table if exists numbers_statistics_all drop constraint if exists FK1so0d0hpkip5h489s3t6o4nm5;

alter table if exists numbers_statistics_dto drop constraint if exists FKl9yjenx93e54twafl4t6t2j82;

alter table if exists numbers_statistics_dto drop constraint if exists FK4pwbw9jj00actv6m34ykqwbxe;

alter table if exists numbers_statistics_dto drop constraint if exists FKn2xpg6qg280loyvr2e94bxpeu;

alter table if exists numbers_statistics_dto drop constraint if exists FKaxk6kl6ank7fn4di90tso5xvk;

alter table if exists numbers_statistics_dto drop constraint if exists FKmd7l396st6t9rkatmgl1jf3p1;

alter table if exists numbers_statistics_dto drop constraint if exists FKa1b21giym5dyof62bwu32nxs1;

alter table if exists numbers_statistics_dto drop constraint if exists FKneaf95gf9qr1ilaal08orfpdl;

alter table if exists numbers_statistics_dto drop constraint if exists FKkfkf5hdajk42d997p02vy27da;

alter table if exists numbers_statistics_dto drop constraint if exists FK11qottfw6ndng58xscthmunjm;

alter table if exists numbers_statistics_dto drop constraint if exists FK6bu3x77bhfa5b7ldq6plff4c6;

alter table if exists player_dto drop constraint if exists FK1yofm5ikyjme75rqdbfs41suw;

alter table if exists player_dto drop constraint if exists FKra3bkhbividrcdyjtqe1idgk7;

alter table if exists player_dto drop constraint if exists FK9e06vtuy97neaut5lxo70d0jo;

alter table if exists player_dto drop constraint if exists FKhxyt2e1h4166ed7mf4fp0chx;

alter table if exists player_dto drop constraint if exists FKm6nbt2oo9maj07url6ar8ujlk;

alter table if exists player_dto_couple_statistics drop constraint if exists FKk6jy6yy88g7ifxt8t20u8kxhj;

alter table if exists player_dto_couple_statistics drop constraint if exists FKgj5oqenyp3cjvs3j05v8mi1al;

alter table if exists player_dto_roles drop constraint if exists FKmat7yb25vfgdb45657ial5q0e;

alter table if exists player_dto_roles drop constraint if exists FKikvnfpmal3ofycuxjluf147ae;

drop table if exists couple_statistics_all cascade;

drop table if exists couple_statistics_dto cascade;

drop table if exists games_per_number_statistics_all cascade;

drop table if exists games_per_number_statistics_dto cascade;

drop table if exists numbers_statistics_all cascade;

drop table if exists numbers_statistics_dto cascade;

drop table if exists place_all cascade;

drop table if exists place_dto cascade;

drop table if exists player_dto cascade;

drop table if exists player_dto_couple_statistics cascade;

drop table if exists player_dto_roles cascade;

drop table if exists rating_statistics_all cascade;

drop table if exists rating_statistics_dto cascade;

drop table if exists role_dto cascade;

drop table if exists roles_history_statistics_all cascade;

drop table if exists roles_history_statistics_dto cascade;

drop table if exists seriality_statistics_all cascade;

drop table if exists seriality_statistics_dto cascade;

drop table if exists visiting_statistics_all cascade;

drop table if exists visiting_statistics_dto cascade;

drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start 1 increment 1;

create table couple_statistics_all
(
    id                    int8 not null,
    from_date             timestamp,
    games                 int4,
    is_active             boolean,
    nickname_of_mafia_one varchar(255),
    nickname_of_mafia_two varchar(255),
    number                int4,
    percent_of_wins       float4,
    to_date               timestamp,
    uploading_date        timestamp,
    wins                  int4,
    primary key (id)
);

create table couple_statistics_dto
(
    id                    int8 not null,
    from_date             timestamp,
    games                 int8,
    nickname_of_mafia_one varchar(255),
    nickname_of_mafia_two varchar(255),
    percent_of_wins       float8,
    to_date               timestamp,
    wins                  int8,
    primary key (id)
);

create table games_per_number_statistics_all
(
    id                        int8 not null,
    first_shot                int4,
    from_date                 timestamp,
    games_total               int4,
    is_active                 boolean,
    number                    int4,
    percent_first_shot        int4,
    percent_selected_black    int4,
    percent_selected_don      int4,
    percent_selected_red      int4,
    percent_selected_sheriff  int4,
    percent_winning_all_black int4,
    percent_winning_all_red   int4,
    percent_winning_black     int4,
    percent_winning_don       int4,
    percent_winning_red       int4,
    percent_winning_sheriff   int4,
    to_date                   timestamp,
    uploading_date            timestamp,
    primary key (id)
);

create table games_per_number_statistics_dto
(
    id                        int8 not null,
    first_shot                int8,
    from_date                 timestamp,
    games_total               int8,
    number                    int4,
    percent_first_shot        float8,
    percent_selected_black    float8,
    percent_selected_don      float8,
    percent_selected_red      float8,
    percent_selected_sheriff  float8,
    percent_winning_all_black float8,
    percent_winning_all_red   float8,
    percent_winning_black     float8,
    percent_winning_don       float8,
    percent_winning_red       float8,
    percent_winning_sheriff   float8,
    to_date                   timestamp,
    primary key (id)
);

create table numbers_statistics_all
(
    id             int8 not null,
    from_date      timestamp,
    games_total    int4,
    is_active      boolean,
    nickname       varchar(255),
    to_date        timestamp,
    uploading_date timestamp,
    place_eight_id int8,
    place_five_id  int8,
    place_four_id  int8,
    place_nine_id  int8,
    place_one_id   int8,
    place_seven_id int8,
    place_six_id   int8,
    place_ten_id   int8,
    place_three_id int8,
    place_two_id   int8,
    primary key (id)
);

create table numbers_statistics_dto
(
    id             int8 not null,
    from_date      timestamp,
    games_total    int8,
    nickname       varchar(255),
    to_date        timestamp,
    place_eight_id int8,
    place_five_id  int8,
    place_four_id  int8,
    place_nine_id  int8,
    place_one_id   int8,
    place_seven_id int8,
    place_six_id   int8,
    place_ten_id   int8,
    place_three_id int8,
    place_two_id   int8,
    primary key (id)
);

create table place_all
(
    id                int8 not null,
    games_black       int4,
    games_don         int4,
    games_red         int4,
    games_sheriff     int4,
    percent_win_black int4,
    percent_win_red   int4,
    primary key (id)
);

create table place_dto
(
    id                int8 not null,
    games_black       int8,
    games_don         int8,
    games_red         int8,
    games_sheriff     int8,
    percent_win_black float8,
    percent_win_red   float8,
    primary key (id)
);

create table player_dto
(
    id                          int8 not null,
    custom_nickname             varchar(255),
    first_name                  varchar(255),
    games_total                 int8,
    gender                      int4,
    last_name                   varchar(255),
    nickname                    varchar(255),
    photo_url                   varchar(255),
    vk_id                       int8,
    numbers_statistics_id       int8,
    rating_statistics_id        int8,
    roles_history_statistics_id int8,
    seriality_statistics_id     int8,
    visiting_statistics_id      int8,
    primary key (id)
);

create table player_dto_couple_statistics
(
    player_dto_id        int8 not null,
    couple_statistics_id int8 not null
);

create table player_dto_roles
(
    player_dto_id int8 not null,
    roles_id      int8 not null,
    primary key (player_dto_id, roles_id)
);

create table rating_statistics_all
(
    id                int8 not null,
    additional_points float4,
    best_move         float4,
    from_date         timestamp,
    games_black       int4,
    games_don         int4,
    games_red         int4,
    games_sheriff     int4,
    games_total       int4,
    is_active         boolean,
    nickname          varchar(255),
    number            int4,
    points            float4,
    to_date           timestamp,
    uploading_date    timestamp,
    primary key (id)
);

create table rating_statistics_dto
(
    id                int8 not null,
    additional_points float8,
    best_move         float8,
    from_date         timestamp,
    games_black       int8,
    games_don         int8,
    games_red         int8,
    games_sheriff     int8,
    games_total       int8,
    nickname          varchar(255),
    points            float8,
    to_date           timestamp,
    primary key (id)
);

create table role_dto
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);

create table roles_history_statistics_all
(
    id                         int8 not null,
    from_date                  timestamp,
    games_black                int4,
    games_don                  int4,
    games_red                  int4,
    games_sheriff              int4,
    games_total                int4,
    is_active                  boolean,
    nickname                   varchar(255),
    number                     int4,
    percent_best_player        int4,
    percent_first_shooting     int4,
    percent_selected_all_black int4,
    percent_selected_all_red   int4,
    percent_selected_black     int4,
    percent_selected_don       int4,
    percent_selected_red       int4,
    percent_selected_sheriff   int4,
    percent_winning            int4,
    percent_winning_all_black  int4,
    percent_winning_all_red    int4,
    percent_winning_black      int4,
    percent_winning_don        int4,
    percent_winning_red        int4,
    percent_winning_sheriff    int4,
    shooting                   int4,
    to_date                    timestamp,
    uploading_date             timestamp,
    primary key (id)
);

create table roles_history_statistics_dto
(
    id                         int8 not null,
    from_date                  timestamp,
    games_black                int8,
    games_don                  int8,
    games_red                  int8,
    games_sheriff              int8,
    games_total                int8,
    nickname                   varchar(255),
    percent_best_player        float8,
    percent_first_shooting     float8,
    percent_selected_all_black float8,
    percent_selected_all_red   float8,
    percent_selected_black     float8,
    percent_selected_don       float8,
    percent_selected_red       float8,
    percent_selected_sheriff   float8,
    percent_winning            float8,
    percent_winning_all_black  float8,
    percent_winning_all_red    float8,
    percent_winning_black      float8,
    percent_winning_don        float8,
    percent_winning_red        float8,
    percent_winning_sheriff    float8,
    shooting                   int8,
    to_date                    timestamp,
    primary key (id)
);

create table seriality_statistics_all
(
    id                             int8 not null,
    from_date                      timestamp,
    games_total                    int4,
    is_active                      boolean,
    maximum_series_of_defeat       int4,
    maximum_series_of_win          int4,
    nickname                       varchar(255),
    successively_lost_by_black     int4,
    successively_lost_by_don       int4,
    successively_lost_by_red       int4,
    successively_lost_by_sheriff   int4,
    successively_played_by_black   int4,
    successively_played_by_don     int4,
    successively_played_by_red     int4,
    successively_played_by_sheriff int4,
    successively_won_by_black      int4,
    successively_won_by_don        int4,
    successively_won_by_red        int4,
    successively_won_by_sheriff    int4,
    to_date                        timestamp,
    uploading_date                 timestamp,
    primary key (id)
);

create table seriality_statistics_dto
(
    id                             int8 not null,
    from_date                      timestamp,
    games_total                    int8,
    maximum_series_of_defeat       int4,
    maximum_series_of_win          int4,
    nickname                       varchar(255),
    successively_lost_by_black     int4,
    successively_lost_by_don       int4,
    successively_lost_by_red       int4,
    successively_lost_by_sheriff   int4,
    successively_played_by_black   int4,
    successively_played_by_don     int4,
    successively_played_by_red     int4,
    successively_played_by_sheriff int4,
    successively_won_by_black      int4,
    successively_won_by_don        int4,
    successively_won_by_red        int4,
    successively_won_by_sheriff    int4,
    to_date                        timestamp,
    primary key (id)
);

create table visiting_statistics_all
(
    id             int8 not null,
    by_friday      int4,
    by_monday      int4,
    by_saturday    int4,
    by_sunday      int4,
    by_thursday    int4,
    by_tuesday     int4,
    by_wednesday   int4,
    from_date      timestamp,
    is_active      boolean,
    nickname       varchar(255),
    to_date        timestamp,
    uploading_date timestamp,
    primary key (id)
);

create table visiting_statistics_dto
(
    id           int8 not null,
    by_friday    float8,
    by_monday    float8,
    by_saturday  float8,
    by_sunday    float8,
    by_thursday  float8,
    by_tuesday   float8,
    by_wednesday float8,
    from_date    timestamp,
    nickname     varchar(255),
    to_date      timestamp,
    primary key (id)
);

alter table if exists player_dto add constraint UK_5gmjr03jh4uybjx52w4a6jx4k unique (custom_nickname);

alter table if exists player_dto add constraint UK_5w987jkpxq935uh3m47hrjqsa unique (nickname);

alter table if exists player_dto add constraint UK_3ggs9a0myucan5u413kvosuc unique (vk_id);

alter table if exists player_dto_roles add constraint UK_rpc78njjn616xh55jxm7ergm4 unique (roles_id);

alter table if exists numbers_statistics_all add constraint FKpidpxqnpx4ujj9lu0fq0ogu2n foreign key (place_eight_id) references place_all;

alter table if exists numbers_statistics_all add constraint FKq0leo2bb7wlkc70d1j9f5wx2i foreign key (place_five_id) references place_all;

alter table if exists numbers_statistics_all add constraint FKmxru3yfsatkxfj7k12uhd285n foreign key (place_four_id) references place_all;

alter table if exists numbers_statistics_all add constraint FKtb3ofl100ba69kulg6sy80qdy foreign key (place_nine_id) references place_all;

alter table if exists numbers_statistics_all add constraint FKe61qp8cr5i4mc08is9mbw84rc foreign key (place_one_id) references place_all;

alter table if exists numbers_statistics_all add constraint FKprmoxele31xu2130iwfjttd25 foreign key (place_seven_id) references place_all;

alter table if exists numbers_statistics_all add constraint FKbg1wixuh3441cdhs8yhid9j36 foreign key (place_six_id) references place_all;

alter table if exists numbers_statistics_all add constraint FK9lqmu1bodeb1ws253iw9wbduh foreign key (place_ten_id) references place_all;

alter table if exists numbers_statistics_all add constraint FKppc2u90ciopcda9ir108qbkno foreign key (place_three_id) references place_all;

alter table if exists numbers_statistics_all add constraint FK1so0d0hpkip5h489s3t6o4nm5 foreign key (place_two_id) references place_all;

alter table if exists numbers_statistics_dto add constraint FKl9yjenx93e54twafl4t6t2j82 foreign key (place_eight_id) references place_dto;

alter table if exists numbers_statistics_dto add constraint FK4pwbw9jj00actv6m34ykqwbxe foreign key (place_five_id) references place_dto;

alter table if exists numbers_statistics_dto add constraint FKn2xpg6qg280loyvr2e94bxpeu foreign key (place_four_id) references place_dto;

alter table if exists numbers_statistics_dto add constraint FKaxk6kl6ank7fn4di90tso5xvk foreign key (place_nine_id) references place_dto;

alter table if exists numbers_statistics_dto add constraint FKmd7l396st6t9rkatmgl1jf3p1 foreign key (place_one_id) references place_dto;

alter table if exists numbers_statistics_dto add constraint FKa1b21giym5dyof62bwu32nxs1 foreign key (place_seven_id) references place_dto;

alter table if exists numbers_statistics_dto add constraint FKneaf95gf9qr1ilaal08orfpdl foreign key (place_six_id) references place_dto;

alter table if exists numbers_statistics_dto add constraint FKkfkf5hdajk42d997p02vy27da foreign key (place_ten_id) references place_dto;

alter table if exists numbers_statistics_dto add constraint FK11qottfw6ndng58xscthmunjm foreign key (place_three_id) references place_dto;

alter table if exists numbers_statistics_dto add constraint FK6bu3x77bhfa5b7ldq6plff4c6 foreign key (place_two_id) references place_dto;

alter table if exists player_dto add constraint FK1yofm5ikyjme75rqdbfs41suw foreign key (numbers_statistics_id) references numbers_statistics_dto;

alter table if exists player_dto add constraint FKra3bkhbividrcdyjtqe1idgk7 foreign key (rating_statistics_id) references rating_statistics_dto;

alter table if exists player_dto add constraint FK9e06vtuy97neaut5lxo70d0jo foreign key (roles_history_statistics_id) references roles_history_statistics_dto;

alter table if exists player_dto add constraint FKhxyt2e1h4166ed7mf4fp0chx foreign key (seriality_statistics_id) references seriality_statistics_dto;

alter table if exists player_dto add constraint FKm6nbt2oo9maj07url6ar8ujlk foreign key (visiting_statistics_id) references visiting_statistics_dto;

alter table if exists player_dto_couple_statistics add constraint FKk6jy6yy88g7ifxt8t20u8kxhj foreign key (couple_statistics_id) references couple_statistics_dto;

alter table if exists player_dto_couple_statistics add constraint FKgj5oqenyp3cjvs3j05v8mi1al foreign key (player_dto_id) references player_dto;

alter table if exists player_dto_roles add constraint FKmat7yb25vfgdb45657ial5q0e foreign key (roles_id) references role_dto;

alter table if exists player_dto_roles add constraint FKikvnfpmal3ofycuxjluf147ae foreign key (player_dto_id) references player_dto;
