package br.com.empresa.entities;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({EmpresaTest.class, EnderecoTest.class, FuncionarioTest.class, TelefoneTest.class})
public class SuiteStartCase { }
