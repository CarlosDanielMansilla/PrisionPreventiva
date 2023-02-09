async function iniciarSesion(){
    let datos ={};

    datos.email= document.getElementById('email').value;
    datos.password= document.getElementById('password').value;
    
    

    const rawResponse = await fetch('app/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  const usuarios = await rawResponse.text();

  if(usuarios != "Fail"){
    localStorage.token = usuarios;
    localStorage.email = datos.email;
    window.location.href="listado.html"
  }
 else{
    alert("credenciales incorrectas")
 }
  
}