/**
 * @author Matteo Nicolas
 */

function validation() {
	let nome = formRegister.nome.value
	let login = formRegister.login.value
	let password = formRegister.password.value
	if (nome === "") {
		alert('Informe um nome!')
		formRegister.nome.focus()
		return false
	} else if (login === "") {
		alert('Informe um nome de usuário!')
		formRegister.login.focus()
		return false
	} else if (password === "") {
		alert('Informe a senha!')
		formRegister.password.focus()
		return false
	} else {
		document.forms["formRegister"].submit()
	}
}
