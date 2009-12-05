﻿--[ CONSULTAS ]--
-- SELECT * FROM T_PESSOA P, T_USUARIO U WHERE P.ID_PESSOA = U.ID_PESSOA

-- SELECT * FROM T_PRELETOR PT, T_USUARIO U WHERE PT.ID_USUARIO = U.ID_USUARIO

-- SELECT * FROM T_CENTROCONV C, T_SALA SL, T_STAND ST WHERE C.id_cconv = SL.id_cconv AND C.id_cconv = ST.id_cconv

-- SELECT C.TITULO, C.INICIO, C.TERMINO, S.TEMA FROM T_CONFERENCIA C, T_SESSOESCONF S WHERE C.ID_CONF = S.ID_CONF

--[ INSERCOES ]
INSERT INTO T_CENTROCONV VALUES ('Av. Reboucas 450 - S.P.', '11 35212211', '11 35212211', 1, 'Reboucas')

INSERT INTO T_SALA VALUES (250, 'SALA AMARELA', 1, 1)

INSERT INTO T_STAND VALUES (1, 250, 1, 1)

INSERT INTO T_CONFERENCIA VALUES ('Simpósio Soluções Google para ambiente corporativo', 'Av. Reboucas 450 - S.P.', 1, 1, '2009-11-21', '2009-10-16', '2009-12-16','2009-11-14','Soluções Google para ambiente corporativo')

INSERT INTO T_SESSOESCONF VALUES ('2009-11-28', 1, 1, 1, 1, 'Segurança Google para aplicações Web')

INSERT INTO T_SESSOESCONF VALUES ('2009-11-29', 1, 1, 1, 1, 'Teste de sessoes 1')

INSERT INTO T_SESSOESCONF VALUES ('2009-11-30', 1, 1, 1, 1, 'Teste de sessoes 2')

--[PRELETOR I]
INSERT INTO T_PESSOA VALUES ('', '01132460006', 1258794607605, 'DAVID', 'VISE')

INSERT INTO T_USUARIO VALUES ('2009-11-21', 'DAVID.VISE@GMAIL.COM', 1258794607605, 1258794607606, 'S', 'teste123')

INSERT INTO T_PRELETOR (ID_USUARIO, INSTITUICAO) VALUES (1258794607606, 'GOOGLE INC.')

INSERT INTO T_TRABALHO VALUES (1258794607606, 1, 'Como é encarada a Segurança de Sistemas pela Google', 'Segurança na perspectiva Google')

INSERT INTO T_APRES (duracao, horarioInicio, id_apres, id_sessaoconf, id_usuario) VALUES (2, 10, 1, 1, 1258794607606)

--[PRELETOR II]
INSERT INTO T_PESSOA VALUES ('', '', 1258794607607, 'MARK', 'MALSEED')

INSERT INTO T_USUARIO VALUES ('2009-11-14', 'MARK.MALSEED@GMAIL.COM', 1258794607607, 1258794607608, 'S', 'teste123')

INSERT INTO T_PRELETOR (ID_USUARIO, INSTITUICAO) VALUES (1258794607608, 'GOOGLE INC.')

INSERT INTO T_TRABALHO VALUES (1258794607608, 2, 'Serviços hospedados de arquivamento e segurança para o seu negócio', 'Google postini services')

INSERT INTO T_APRES (duracao, horarioInicio, id_apres, id_sessaoconf, id_usuario) VALUES (2, 13, 2, 1, 1258794607608)