INSERT INTO lancamento 
(id_lancamento, data, historico, lancamentotipo, valor, credito, debito, exercicio, lancamento_principal) 
VALUES 
(100, '2016-12-31', 'dsadasdasdas', 'SIMPLES', 123, 315, 203, 2, NULL);

select nextval('seq_lancamento') ;

select nextval ('SEQ_LANCAMENTO');

	INSERT INTO lancamento 
	(id_lancamento, data, historico, lancamentotipo, valor, credito, debito, exercicio, lancamento_principal) 
	VALUES 
	(nextval ('SEQ_LANCAMENTO'), '2016-12-31', 'dsadasdasdas', 'SIMPLES', 1, 203, 315, 2, NULL);

