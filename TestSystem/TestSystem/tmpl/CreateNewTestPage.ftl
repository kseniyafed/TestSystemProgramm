<!DOCTYPE html>
<html>
    <head>
        <title>TestSystem</title>
        <meta charset="UTF-8">
        <style>
          
            .block1 { 
                width:95%;
                padding: 10px;  
                font-size: 80%; 
                text-align: right
            }
            .block2{    
                width: 200%;
                height: 50px;
                padding: 5px;
                position:relative;
                left:5%;
                top:25px;
                color: #191970; 
                font-size: 150%;
            }
           
        </style>
    </head>
    <body>
        <body bgcolor="#ADD8E6">
        <body bgcolor="#ADD8E6">
        <div class="block1">
            <a href="/">${login} Выход</a>
        </div>
        <h1 align="center"><font color="#191970">Создание теста</font></h1>
        <h1 align="center"><font color="#191970">${subject.name}</font></h1>
        
        <form action="/createTestPage/${subject.idSubject}" method="POST">
            <div class="block2">
                <label for="number">Номер вопроса: ${quesNum}&nbsp</label>
                
            </div>
            <div class="block2">
                <label for="question">Вопрос: &nbsp</label>
                <input type="text" class="form-control" id="question" name="question" placeholder="Введите вопрос" size="100%" style="font-size: 20px"></input>
            </div>
            <div class="block2">
                <label for="answer">Ответ:</label>  
                <input type="text" class="form-control" id="answer" name="answer" placeholder="Введите ответ" size="100%" style="font-size: 20px"></input>
            </div>
            <p style="text-align: center">
                <button type="submit" >Добавить вопрос</button>
            </p> 
            </form>
            <form action="/teacherPage" method="POST">
                <p style="text-align: center">      
                    <button type="submit" >На главную</button>
                </p>  
            </form>


    </body>
</html>
