package com.example.task.TaskTrakerController.Model;


import com.example.task.ApiResponse.ApiResponse;
import com.example.task.Model.Traker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/task")
public class taskController {

    ArrayList <Traker> trakerList = new ArrayList<>();
    @GetMapping("/List")
    public ArrayList<Traker> getTraker()
    {
        return trakerList;
    }

    @PostMapping("/add")
    public ApiResponse addTodo(@RequestBody Traker traker)
    {
        trakerList.add(traker);
        return  new ApiResponse("Added Task Successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTodo(@PathVariable int index, @RequestBody Traker traker)
    {

        trakerList.set(index,traker);

        return new ApiResponse("Updated Task Successfully ");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTodo(@PathVariable int index) {
        trakerList.remove(index);
        return new ApiResponse("Deleted Successfully");
    }

    @PutMapping("/checkStatus/{id}")
    public ApiResponse updateTask(@PathVariable String id, @RequestBody Traker updateTask) {

        for (Traker task : trakerList)
        {
            if (task.getID().equals(id)) {
                if (task.getStatus() != "Done") {
                 task.setStatus("Done");
                return new ApiResponse("Task not Done, updated successfully to Done");
            }
        }
        return new ApiResponse("Task not found");
    }
        return new ApiResponse("Task Already Done");
    }

    @PutMapping("/findTask/{getValue}")
    public ApiResponse SearchTask(@PathVariable String getValue)
    {
        for (Traker traker : trakerList) {

            if (traker.getTitle().equals(getValue))
            {
                return  new ApiResponse("We Find The Task");
            }

        }
        return  new ApiResponse("Not found");
    }

}
