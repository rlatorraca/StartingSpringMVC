$(function() {
	// $('[data-toggle="tooltip"]').tooltip()
	$('[rel="tooltip"]').tooltip();
	//$("body").tooltip({ selector: '[rel="tooltip"]' });
});


$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget); // pega o botao que disparou o evento
	
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this); // tranforma o MODAL em um objeto do Jquery
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
});