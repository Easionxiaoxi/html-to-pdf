<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>报名表</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .a4 {
            width: 100%;
            margin: 0 auto;
            padding: 0 10px;
            font-size: 12px;
            font-weight: 700;
        }

        em {
            font-style: normal;
        }

        /*设置模板字体，对应配置的font字体*/
        body {
            font-family: SimSun;
        }

        section {
            display: block;
        }

        /*表格分页时，超出分页，在下一页显示*/
        table {
            page-break-inside: auto;
            -fs-table-paginate: paginate;
            word-break: break-word;
        }

        /*表格行分页时，超出分页，在下一页显示*/
        tr {
            page-break-inside: avoid;
            page-break-after: auto;
        }

        /*强制分页*/
        .pageNext {
            page-break-after: always;
        }

        /*页码*/
        @page {
            /*size: 8.5in 11in;*/
            @bottom-right {
                content: "page " counter(page) " of  " counter(pages);
            }
        }
    </style>
</head>
<body>
<#--div要用section替换-->
<section class="a4">
    <#--表名-->
    <h4 style="margin-top: 20px; text-align: center;">报名表</h4>
    <#--岗位-->
    <div style="display: flex;">
        <h4>应聘岗位：</h4>
    </div>
</section>

</body>
</html>