/**
 * @author Matteo Nicolas
 */

function confirmation(id){
	let resp = confirm("Você confirma a exclusão?")
	if (resp === true){
		alert("O usuário id: " + id + " foi excluído.")
		window.location.href = "delete?id=" + id
	}
}