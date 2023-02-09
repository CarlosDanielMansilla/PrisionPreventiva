// $(document).ready(function(){
//     cargarInternos();
//     $('#internos').DataTable(dataTableOptions);
    
// });

window.addEventListener("load", async () => {
  await cargarInternos();
  $('#internos').DataTable(dataTableOptions);
});

const dataTableOptions ={


  columnDefs: [
    { orderable: false, targets: [3, 7, 8] },
    { searchable: false, targets: [0,3,4,5,6,7,8] },
    { width: "8%", targets: [4, 5 , 6] }
],
  //pageLength: 3,
  language: {
    lengthMenu: "Mostrar _MENU_ registros por página",
    zeroRecords: "Ningún usuario encontrado",
    info: "Mostrando de _START_ a _END_ de un total de _TOTAL_ registros",
    infoEmpty: "Ningún usuario encontrado",
    infoFiltered: "(filtrados desde _MAX_ registros totales)",
    search: "Buscar:",
    loadingRecords: "Cargando...",
    paginate: {
        first: "Primero",
        last: "Último",
        next: "Siguiente",
        previous: "Anterior"
    }
}
}



async function cargarInternos(){

    const rawResponse = await fetch('app/internos', {
    method: 'GET',
    headers: getHeaders()
  });
  const internos = await rawResponse.json();
  

  let listadoHtml = '';
  for(let interno of internos){
    let btnEliminar = '<a href="#" onclick="eliminarInterno('+ interno.id +')"><i class="fa-solid fa-trash"></i></a>';

    let fechaActual = new Date();
    let fecha1 = moment(fechaActual);
    let fecha2 = moment(interno.fechaLimite);
    let alerta = fecha2.diff(fecha1, 'days');
    let dias;

    if(alerta < 0){
        dias= "Se cumplio los dos años"
    }
    else if(alerta < 243){
      dias= `<div style="color: #FF0032;"> Faltan ` + alerta + ` dias </div>`;
    }
    else if(alerta < 486){
      dias= `<div style="color: #F99417;"> Faltan ` + alerta + ` dias </div>`;
    }
    else{
      dias= `<div style="color: #439A97;"> Faltan ` + alerta + ` dias </div>`;
    }

    

    
    let datos= `<tr row_id="`+interno.id+`"> 
    <td><div class="row_data" edit_type="click" col_name="circunscripcion">` + interno.circunscripcion + `</div></td> 
    <td><div class="row_data" edit_type="click" col_name="fiscalia">` + interno.fiscalia + `</div></td> 
    <td><div class="row_data" edit_type="click" col_name="nombreCompleto">` + interno.nombreCompleto + `</div></td> 
    <td><div class="row_data" edit_type="click" col_name="expediente">`+ interno.expediente +`</div></td> 
    <td><div class="row_data" edit_type="click" col_name="fechaDetenido">`+ interno.fechaDetenido +`</div></td> 
    <td><div class="row_data" edit_type="click" col_name="fechaPreventiva">`+ interno.fechaPreventiva +`</div></td> 
    <td><div class="row_data" edit_type="click" col_name="fechaLimite">`+ interno.fechaLimite +`</div></td> 
    <td> `+ dias +` </td>
    <td><div class="row_data" edit_type="click" col_name="observaciones">`+ interno.observaciones +`</div></td> 
    <td>`+ btnEliminar +`</td>
    </tr>`;

    
    listadoHtml += datos;
  }

  document.querySelector('#internos tbody').outerHTML= listadoHtml;

}



$(document).on('click', '.row_data', function(event) 
{
	event.preventDefault(); 

	if($(this).attr('edit_type') == 'button')
	{
		return false; 
	}

	//make div editable
	$(this).closest('div').attr('contenteditable', 'true');
	//add bg css
	$(this).addClass('bg-warning').css('padding','5px');

	$(this).focus();
})	



$(document).on('focusout', '.row_data', function(event) 
{
	event.preventDefault();

	if($(this).attr('edit_type') == 'button')
	{
		return false; 
	}

	var row_id = $(this).closest('tr').attr('row_id'); 
	
	var row_div = $(this)			
	.removeClass('bg-warning') //add bg css
	.css('padding','')

	var col_name = row_div.attr('col_name'); 
	var col_val = row_div.html(); 

	var arr = {};
	arr[col_name] = col_val;

	//use the "arr"	object for your ajax call
	$.extend(arr, {row_id:row_id});

	//out put to show
	$('.post_msg').html( '<pre class="bg-success">'+JSON.stringify(arr, null, 2) +'</pre>');
	editarInterno(row_id,arr)
  
})	


function getHeaders() {
  return {
   'Accept': 'application/json',
   'Content-Type': 'application/json',
   'Authorization': localStorage.token
 };
}

async function eliminarInterno(id){

    if(!confirm('¿Desea eliminar?'))
    {
        return;
    }

    const rawResponse = await fetch('app/interno/'+ id, {
        method: 'DELETE',
        headers: getHeaders()
      });
    
    location.reload();
}


async function editarInterno(id,arr){

  let datos ={};

    datos.nombreCompleto= "leo2";
    datos.circunscripcion= '';
    datos.fiscalia= arr.fiscalia;
    datos.expediente= '';
    datos.observaciones= '';
    datos.fechaDetenido= '';
    datos.fechaLimite= '';
    datos.fechaPreventiva= '';
    

    const rawResponse = await fetch('app/interno/'+ id, {
    method: 'PUT',
    headers: getHeaders(),
    body: JSON.stringify(arr)
  });
  
  location.reload();
}




async function agregarInternos(){
    let datos ={};

    datos.nombreCompleto= document.getElementById('nombre').value;
    datos.circunscripcion= document.getElementById('circunscripcion').value;
    datos.fiscalia= document.getElementById('fiscalia').value;
    datos.expediente= document.getElementById('exp').value;
    datos.observaciones= document.getElementById('obs').value;
    datos.fechaDetenido= document.getElementById('fechaD').value;
    datos.fechaLimite= document.getElementById('fechaL').value;
    datos.fechaPreventiva= document.getElementById('fechaP').value;
    

    const rawResponse = await fetch('app/internos', {
    method: 'POST',
    headers: getHeaders(),
    body: JSON.stringify(datos)
  });

  alert("La cuenta fue creada con exito!");
  window.location.href = "../listado.html";
}

