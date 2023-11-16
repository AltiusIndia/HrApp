<%-- 
    Document   : script
    Created on : 1 Oct, 2016, 11:11:14 AM
    Author     : shrutika
--%>

<!-- START PRELOADS -->
<audio id="audio-alert" src="../audio/alert.mp3" preload="auto"></audio>
<audio id="audio-fail" src="../audio/fail.mp3" preload="auto"></audio>
<!-- END PRELOADS -->                  

<!-- START SCRIPTS -->
<!-- START PLUGINS -->
<script type="text/javascript" src="../js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="../js/plugins/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/plugins/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/plugins/bootstrap/bootstrap-datepicker.js"></script>

<!-- END PLUGINS -->

<script type="text/javascript">

    $(".datepicker1").datepicker({
        dateFormat: 'yy-mm-dd',
        changeMonth: true,
        changeYear: true,
        yearRange: '-100y:c+nn',
        maxDate: '-1d',
        autoclose: true
    });

    history.pushState(null, document.title, location.href);
    window.addEventListener('popstate', function (event)
    {
        history.pushState(null, document.title, location.href);
    });
</script>

<!-- END SCRIPTS -->         