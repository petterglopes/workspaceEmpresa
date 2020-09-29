package br.com.empresa.template;

import org.joda.time.LocalDate;

import br.com.empresa.entities.Empresa;
import br.com.empresa.entities.Endereco;
import br.com.empresa.entities.Funcionario;
import br.com.empresa.entities.Telefone;
import br.com.empresa.entities.TipoEndereco;
import br.com.empresa.entities.TipoTelefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class TemplatesFixtureFactory implements TemplateLoader {

    @Override
    public void load() {

        Fixture.of(Empresa.class).addTemplate("empresa_valida", new Rule() {
            {
                add("id", null);
                add("nome", "Empresa");
                add("cnpj", cnpj());
                add("representanteLegal", one(Funcionario.class, "funcionario_valido"));
                add("enderecos", has(1).of(Endereco.class, "endereco_valido"));
                add("telefones", has(1).of(Telefone.class, "telefone_valido"));
                add("dataAbertura", LocalDate.now());
                add("site", "https://contmatic.com.br/");
            }
        }).addTemplate("empresa_nome_null").inherits("empresa_valida", new Rule() {
            {
                add("nome", null);
            }
        }).addTemplate("empresa_nome_caractere_numerico").inherits("empresa_valida", new Rule() {
            {
                add("nome", "Empresa1");
            }
        }).addTemplate("empresa_nome_menos_de_3_caracteres").inherits("empresa_valida", new Rule() {
            {
                add("nome", "Em");
            }
        }).addTemplate("empresa_nome_mais_de_100_caracteres").inherits("empresa_valida", new Rule() {
            {
                add("nome", regex("[A-Z]{101}"));
            }
        }).addTemplate("empresa_nome_vazio").inherits("empresa_valida", new Rule() {
            {
                add("nome", "");
            }
        }).addTemplate("empresa_cnpj_caracteres_especiais").inherits("empresa_valida", new Rule() {
            {
                add("cnpj", cnpj(true));
            }
        }).addTemplate("empresa_cnpj_invalido").inherits("empresa_valida", new Rule() {
            {
                add("cnpj", "48745264000105");
            }
        }).addTemplate("empresa_cnpj_null").inherits("empresa_valida", new Rule() {
            {
                add("cnpj", null);
            }
        }).addTemplate("empresa_cnpj_vazio").inherits("empresa_valida", new Rule() {
            {
                add("cnpj", "");
            }
        }).addTemplate("empresa_cnpj_quantidade_maior").inherits("empresa_valida", new Rule() {
            {
                add("cnpj", cnpj() + "1");
            }
        }).addTemplate("empresa_cnpj_quantidade_menor").inherits("empresa_valida", new Rule() {
            {
                add("cnpj", cnpj().toString().replace("0", ""));
            }
        }).addTemplate("empresa_representante_legal_null").inherits("empresa_valida", new Rule() {
            {
                add("representanteLegal", null);
            }
        }).addTemplate("empresa_representante_legal_invalido").inherits("empresa_valida", new Rule() {
            {
                add("representanteLegal", new Funcionario());
            }
        }).addTemplate("empresa_enderecos_null").inherits("empresa_valida", new Rule() {
            {
                add("enderecos", null);
            }
        }).addTemplate("empresa_telefones_null").inherits("empresa_valida", new Rule() {
            {
                add("telefones", null);
            }
        }).addTemplate("empresa_dataAbertura_null").inherits("empresa_valida", new Rule() {
            {
                add("dataAbertura", null);
            }
        }).addTemplate("empresa_dataAbertura_futuro").inherits("empresa_valida", new Rule() {
            {
                add("dataAbertura", LocalDate.now().plusDays(1));
            }
        }).addTemplate("empresa_site_null").inherits("empresa_valida", new Rule() {
            {
                add("site", null);
            }
        }).addTemplate("empresa_site_vazio").inherits("empresa_valida", new Rule() {
            {
                add("site", "");
            }
        }).addTemplate("empresa_site_texto_compativel").inherits("empresa_valida", new Rule() {
            {
                add("site", "contmatic");
            }
        });

        Fixture.of(Funcionario.class).addTemplate("funcionario_valido", new Rule() {
            {
                add("id", null);
                add("nome", "Antonio");
                add("cpf", "11164291602");
                add("salario", 3000L);
                add("enderecos", has(1).of(Endereco.class, "endereco_valido"));
                add("telefones", has(1).of(Telefone.class, "telefone_valido"));
                add("email", "asdakjm@asdkjs.com");
            }
        }).addTemplate("funcionario_nome_null").inherits("funcionario_valido", new Rule() {
            {
                add("nome", null);
            }
        }).addTemplate("funcionario_nome_caractere_numerico").inherits("funcionario_valido", new Rule() {
            {
                add("nome", "Empresa1");
            }
        }).addTemplate("funcionario_nome_menos_de_3_caracteres").inherits("funcionario_valido", new Rule() {
            {
                add("nome", "Em");
            }
        }).addTemplate("funcionario_nome_mais_de_100_caracteres").inherits("funcionario_valido", new Rule() {
            {
                add("nome", regex("[A-Z]{101}"));
            }
        }).addTemplate("funcionario_nome_vazio").inherits("funcionario_valido", new Rule() {
            {
                add("nome", "");
            }
        }).addTemplate("funcionario_cpf_caracteres_especiais").inherits("funcionario_valido", new Rule() {
            {
                add("cpf", "111.642.916-02");
            }
        }).addTemplate("funcionario_cpf_invalido").inherits("funcionario_valido", new Rule() {
            {
                add("cpf", "111.642.916-03");
            }
        }).addTemplate("funcionario_cpf_null").inherits("funcionario_valido", new Rule() {
            {
                add("cpf", null);
            }
        }).addTemplate("funcionario_cpf_vazio").inherits("funcionario_valido", new Rule() {
            {
                add("cpf", "");
            }
        }).addTemplate("funcionario_cpf_quantidade_maior").inherits("funcionario_valido", new Rule() {
            {
                add("cpf", "111642916021");
            }
        }).addTemplate("funcionario_cpf_quantidade_menor").inherits("funcionario_valido", new Rule() {
            {
                add("cpf", "1116429160");
            }
        }).addTemplate("funcionario_enderecos_null").inherits("funcionario_valido", new Rule() {
            {
                add("enderecos", null);
            }
        }).addTemplate("funcionario_telefones_null").inherits("funcionario_valido", new Rule() {
            {
                add("telefones", null);
            }
        }).addTemplate("funcionario_email_invalido").inherits("funcionario_valido", new Rule() {
            {
                add("email", "askldj.asdf");
            }
        }).addTemplate("funcionario_email_null").inherits("funcionario_valido", new Rule() {
            {
                add("email", null);
            }
        }).addTemplate("funcionario_email_vazio").inherits("funcionario_valido", new Rule() {
            {
                add("email", "");
            }
        });

        Fixture.of(Endereco.class).addTemplate("endereco_valido", new Rule() {
            {
                add("id", null);
                add("logradouro", "Rua");
                add("enderecoLogradouro", random("7 de Abril", "9 de Julho"));
                add("numero", 1000);
                add("bairro", "Centro");
                add("cidade", "Sao Paulo");
                add("estado", "SP");
                add("pais", "Brasil");
                add("cep", "11111-111");
                add("tipoEndereco", TipoEndereco.RESIDENCIAL);
            }
        }).addTemplate("endereco_valido_comercial").inherits("endereco_valido", new Rule() {
            {
                add("tipoEndereco", TipoEndereco.COMERCIAL);
            }
        }).addTemplate("endereco_valido_residencial").inherits("endereco_valido", new Rule() {
            {
                add("tipoEndereco", TipoEndereco.RESIDENCIAL);
            }
        }).addTemplate("endereco_logradouro_null").inherits("endereco_valido", new Rule() {
            {
                add("logradouro", null);
            }
        }).addTemplate("endereco_logradouro_vazio").inherits("endereco_valido", new Rule() {
            {
                add("logradouro", "");
            }
        }).addTemplate("endereco_logradouro_diferente").inherits("endereco_valido", new Rule() {
            {
                add("logradouro", "Estrada");
            }
        }).addTemplate("endereco_endereco_logradouro_null").inherits("endereco_valido", new Rule() {
            {
                add("enderecoLogradouro", null);
            }
        }).addTemplate("endereco_endereco_logradouro_vazio").inherits("endereco_valido", new Rule() {
            {
                add("enderecoLogradouro", "");
            }
        }).addTemplate("endereco_endereco_logradouro_diferente_letra_num").inherits("endereco_valido", new Rule() {
            {
                add("enderecoLogradouro", "@#$");
            }
        }).addTemplate("endereco_numero_menor_que_zero").inherits("endereco_valido", new Rule() {
            {
                add("numero", -9);
            }
        }).addTemplate("endereco_numero_null").inherits("endereco_valido", new Rule() {
            {
                add("numero", null);
            }
        }).addTemplate("endereco_bairro_vazio").inherits("endereco_valido", new Rule() {
            {
                add("bairro", "");
            }
        }).addTemplate("endereco_bairro_null").inherits("endereco_valido", new Rule() {
            {
                add("bairro", null);
            }
        }).addTemplate("endereco_cidade_vazia").inherits("endereco_valido", new Rule() {
            {
                add("cidade", "");
            }
        }).addTemplate("endereco_cidade_null").inherits("endereco_valido", new Rule() {
            {
                add("cidade", null);
            }
        }).addTemplate("endereco_estado_valor_diferente_sigla").inherits("endereco_valido", new Rule() {
            {
                add("estado", "Minas Gerais");
            }
        }).addTemplate("endereco_estado_vazio").inherits("endereco_valido", new Rule() {
            {
                add("estado", "");
            }
        }).addTemplate("endereco_estado_null").inherits("endereco_valido", new Rule() {
            {
                add("estado", null);
            }
        }).addTemplate("endereco_pais_vazio").inherits("endereco_valido", new Rule() {
            {
                add("pais", "");
            }
        }).addTemplate("endereco_pais_null").inherits("endereco_valido", new Rule() {
            {
                add("pais", null);
            }
        }).addTemplate("endereco_cep_diferente_formato").inherits("endereco_valido", new Rule() {
            {
                add("cep", "22222222");
            }
        }).addTemplate("endereco_cep_vazio").inherits("endereco_valido", new Rule() {
            {
                add("cep", "");
            }
        }).addTemplate("endereco_cep_null").inherits("endereco_valido", new Rule() {
            {
                add("cep", null);
            }
        }).addTemplate("endereco_tipo_endereco_null").inherits("endereco_valido", new Rule() {
            {
                add("tipoEndereco", null);
            }
        });

        Fixture.of(Telefone.class).addTemplate("telefone_valido", new Rule() {
            {
                add("id", null);
                add("ddd", regex("[0-9]{2}"));
                add("numero", regex("[0-9]{5}[-][0-9]{4}"));
                add("operadora", random("Vivo", "Tim", "Oi", "Claro"));
                add("tipoTelefone", TipoTelefone.RESIDENCIAL);
            }
        }).addTemplate("telefone_valido_comercial").inherits("telefone_valido", new Rule() {
            {
                add("tipoTelefone", TipoTelefone.COMERCIAL);
            }
        }).addTemplate("telefone_valido_residencial").inherits("telefone_valido", new Rule() {
            {
                add("tipoTelefone", TipoTelefone.RESIDENCIAL);
            }
        }).addTemplate("telefone_ddd_diferente_formato").inherits("telefone_valido", new Rule() {
            {
                add("ddd", "111");
            }
        }).addTemplate("telefone_ddd_vazio").inherits("telefone_valido", new Rule() {
            {
                add("ddd", "");
            }
        }).addTemplate("telefone_ddd_null").inherits("telefone_valido", new Rule() {
            {
                add("ddd", null);
            }
        }).addTemplate("telefone_numero_diferente_formato").inherits("telefone_valido", new Rule() {
            {
                add("numero", "111111111");
            }
        }).addTemplate("telefone_numero_vazio").inherits("telefone_valido", new Rule() {
            {
                add("numero", "");
            }
        }).addTemplate("telefone_numero_null").inherits("telefone_valido", new Rule() {
            {
                add("numero", null);
            }
        }).addTemplate("telefone_operadora_vazio").inherits("telefone_valido", new Rule() {
            {
                add("operadora", "");
            }
        }).addTemplate("telefone_operadora_null").inherits("telefone_valido", new Rule() {
            {
                add("operadora", null);
            }
        }).addTemplate("telefone_tipo_telefone_null").inherits("telefone_valido", new Rule() {
            {
                add("tipoTelefone", null);
            }
        });
    }
}
