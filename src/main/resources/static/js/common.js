// A $( document ).ready() block.
$( document ).ready(function() {
    $('.datepicker').datepicker({
        format: 'yyyy/mm/dd',
        startDate: '-3d'
    });
});
