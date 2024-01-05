CREATE TABLE couple_statistics_all
(
    id                    int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE couple_statistics_dto
(
    id                         int8 NOT NULL,
    calculated_percent_of_wins int8,
    from_date                  timestamp,
    games                      int8,
    nickname_of_mafia_one      varchar(255),
    nickname_of_mafia_two      varchar(255),
    percent_of_wins            float8,
    to_date                    timestamp,
    wins                       int8,
    PRIMARY KEY (id)
);

CREATE TABLE games_per_number_statistics_all
(
    id                        int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE games_per_number_statistics_dto
(
    id                        int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE numbers_statistics_all
(
    id             int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE numbers_statistics_dto
(
    id             int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE place_all
(
    id                int8 NOT NULL,
    games_black       int4,
    games_don         int4,
    games_red         int4,
    games_sheriff     int4,
    percent_win_black int4,
    percent_win_red   int4,
    PRIMARY KEY (id)
);

CREATE TABLE place_dto
(
    id                int8 NOT NULL,
    games_black       int8,
    games_don         int8,
    games_red         int8,
    games_sheriff     int8,
    percent_win_black float8,
    percent_win_red   float8,
    PRIMARY KEY (id)
);

CREATE TABLE player_dto
(
    id                          int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE player_dto_couple_statistics
(
    player_dto_id        int8 NOT NULL,
    couple_statistics_id int8 NOT NULL
);

CREATE TABLE player_dto_roles
(
    player_dto_id int8 NOT NULL,
    roles_id      int8 NOT NULL,
    PRIMARY KEY (player_dto_id, roles_id)
);

CREATE TABLE rating_statistics_all
(
    id                int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE rating_statistics_dto
(
    id                int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE role_dto
(
    id   int8 NOT NULL,
    name varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE roles_history_statistics_all
(
    id                         int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE roles_history_statistics_dto
(
    id                         int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE seriality_statistics_all
(
    id                             int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE seriality_statistics_dto
(
    id                             int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE visiting_statistics_all
(
    id             int8 NOT NULL,
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
    PRIMARY KEY (id)
);

CREATE TABLE visiting_statistics_dto
(
    id           int8 NOT NULL,
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
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS player_dto
    DROP CONSTRAINT IF EXISTS UK_5gmjr03jh4uybjx52w4a6jx4k;

ALTER TABLE IF EXISTS player_dto
    ADD CONSTRAINT UK_5gmjr03jh4uybjx52w4a6jx4k UNIQUE (custom_nickname);

ALTER TABLE IF EXISTS player_dto
    DROP CONSTRAINT IF EXISTS UK_5w987jkpxq935uh3m47hrjqsa;

ALTER TABLE IF EXISTS player_dto
    ADD CONSTRAINT UK_5w987jkpxq935uh3m47hrjqsa UNIQUE (nickname);

ALTER TABLE IF EXISTS player_dto
    DROP CONSTRAINT IF EXISTS UK_3ggs9a0myucan5u413kvosuc;

ALTER TABLE IF EXISTS player_dto
    ADD CONSTRAINT UK_3ggs9a0myucan5u413kvosuc UNIQUE (vk_id);

ALTER TABLE IF EXISTS player_dto_roles
    DROP CONSTRAINT IF EXISTS UK_rpc78njjn616xh55jxm7ergm4;

ALTER TABLE IF EXISTS player_dto_roles
    ADD CONSTRAINT UK_rpc78njjn616xh55jxm7ergm4 UNIQUE (roles_id);

CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FKpidpxqnpx4ujj9lu0fq0ogu2n FOREIGN KEY (place_eight_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FKq0leo2bb7wlkc70d1j9f5wx2i FOREIGN KEY (place_five_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FKmxru3yfsatkxfj7k12uhd285n FOREIGN KEY (place_four_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FKtb3ofl100ba69kulg6sy80qdy FOREIGN KEY (place_nine_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FKe61qp8cr5i4mc08is9mbw84rc FOREIGN KEY (place_one_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FKprmoxele31xu2130iwfjttd25 FOREIGN KEY (place_seven_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FKbg1wixuh3441cdhs8yhid9j36 FOREIGN KEY (place_six_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FK9lqmu1bodeb1ws253iw9wbduh FOREIGN KEY (place_ten_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FKppc2u90ciopcda9ir108qbkno FOREIGN KEY (place_three_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_all
    ADD CONSTRAINT FK1so0d0hpkip5h489s3t6o4nm5 FOREIGN KEY (place_two_id) REFERENCES place_all;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FKl9yjenx93e54twafl4t6t2j82 FOREIGN KEY (place_eight_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FK4pwbw9jj00actv6m34ykqwbxe FOREIGN KEY (place_five_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FKn2xpg6qg280loyvr2e94bxpeu FOREIGN KEY (place_four_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FKaxk6kl6ank7fn4di90tso5xvk FOREIGN KEY (place_nine_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FKmd7l396st6t9rkatmgl1jf3p1 FOREIGN KEY (place_one_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FKa1b21giym5dyof62bwu32nxs1 FOREIGN KEY (place_seven_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FKneaf95gf9qr1ilaal08orfpdl FOREIGN KEY (place_six_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FKkfkf5hdajk42d997p02vy27da FOREIGN KEY (place_ten_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FK11qottfw6ndng58xscthmunjm FOREIGN KEY (place_three_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS numbers_statistics_dto
    ADD CONSTRAINT FK6bu3x77bhfa5b7ldq6plff4c6 FOREIGN KEY (place_two_id) REFERENCES place_dto;

ALTER TABLE IF EXISTS player_dto
    ADD CONSTRAINT FK1yofm5ikyjme75rqdbfs41suw FOREIGN KEY (numbers_statistics_id) REFERENCES numbers_statistics_dto;

ALTER TABLE IF EXISTS player_dto
    ADD CONSTRAINT FKra3bkhbividrcdyjtqe1idgk7 FOREIGN KEY (rating_statistics_id) REFERENCES rating_statistics_dto;

ALTER TABLE IF EXISTS player_dto
    ADD CONSTRAINT FK9e06vtuy97neaut5lxo70d0jo FOREIGN KEY (roles_history_statistics_id) REFERENCES roles_history_statistics_dto;

ALTER TABLE IF EXISTS player_dto
    ADD CONSTRAINT FKhxyt2e1h4166ed7mf4fp0chx FOREIGN KEY (seriality_statistics_id) REFERENCES seriality_statistics_dto;

ALTER TABLE IF EXISTS player_dto
    ADD CONSTRAINT FKm6nbt2oo9maj07url6ar8ujlk FOREIGN KEY (visiting_statistics_id) REFERENCES visiting_statistics_dto;

ALTER TABLE IF EXISTS player_dto_couple_statistics
    ADD CONSTRAINT FKk6jy6yy88g7ifxt8t20u8kxhj FOREIGN KEY (couple_statistics_id) REFERENCES couple_statistics_dto;

ALTER TABLE IF EXISTS player_dto_couple_statistics
    ADD CONSTRAINT FKgj5oqenyp3cjvs3j05v8mi1al FOREIGN KEY (player_dto_id) REFERENCES player_dto;

ALTER TABLE IF EXISTS player_dto_roles
    ADD CONSTRAINT FKmat7yb25vfgdb45657ial5q0e FOREIGN KEY (roles_id) REFERENCES role_dto;

ALTER TABLE IF EXISTS player_dto_roles
    ADD CONSTRAINT FKikvnfpmal3ofycuxjluf147ae FOREIGN KEY (player_dto_id) REFERENCES player_dto;
