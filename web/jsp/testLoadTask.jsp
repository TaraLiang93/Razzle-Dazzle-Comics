<jsp:include page="header.jsp">
    <jsp:param name="title" value="Test Load Task"/>
</jsp:include>

    <script>
        $(document).ready(function(){
            $.ajax({
                url:"/create/page/loadTask",
                data: {pageID: 4512395720392704},
                type:"POST",
                success: function(object){
                    console.log(object);
                },
                error: function(){
                    alert("Failed to edit the Summary.");
                }
            });
        });
    </script>
    <p>Hello World!</p>
<jsp:include page="footer.jsp"/>
