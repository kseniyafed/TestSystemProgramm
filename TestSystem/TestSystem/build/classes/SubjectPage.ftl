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
                width: 70%;
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
    <div class="block1">
            <a href="/">${login} Выход</a>
    </div>  
    <h1 align="center" style="font-size: 20px"><font color="#191970">${subject.name}</font></h1>
    <form action="/teacherPage/del=${subject.idSubject}" method="POST">
        <p style="text-align: center">      
            <button type="submit">Удалить тему</button>
        </p>        
    </form>
    <form action="/createTestPage/${subject.idSubject}" method="POST">
        <p style="text-align: center">      
            <button type="submit">Создать тест</button>
        </p>        
    </form>
    <#if visible??>
           <form action="/testEditor/${subject.idSubject}" method="POST">
                <p style="text-align: center">      
                    <button type="submit">Редактировать тест</button>
                </p>        
           </form>
    </#if>
    <form action="/subjectPage/${subject.idSubject}" method="POST">
            
            <div class="block2">
                <label for="number">Номер темы: &nbsp</label>
                <input type="text" class="form-control" id="number" name="number" contenteditable="true" value=${subject.number} style="font-size: 20px"></input>
           
            </div>
            
            <div class="block2">
            <p><label for="teory">Теория:</label> </p>
            <p style="text-align: center"><textarea id="teory"  name="teory" cols="150" rows="10" contenteditable="true" value=${subject.teory}" ></textarea>
        
            </p> 
            

            <p style="text-align: left">      
                <input type="button" onclick="history.back();" value="Назад"/> 
                <button type="submit" >Сохранить</button>
            </p> 
            
              </div> 
     </form>
     
    </body>
    </html>   
