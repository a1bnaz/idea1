package com.idea1.app.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idea1.app.backend.model.Note;
import com.idea1.app.backend.service.NoteService;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    
    @Autowired
    private NoteService noteService;

    @GetMapping("/{userId}/all")
    public List<Note> getAllNotesFromUser(@PathVariable String username){
        return noteService.getAllNotesFromUser(username);
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable Long id){

        return noteService.getNoteById(id);

    }
    
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note, String username){
        Note newNote = noteService.createNote(note, username);

        return ResponseEntity.ok(newNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody Long noteId, Note updatedData, String username){
        Note updatedNote = noteService.updateNote(noteId, updatedData, username);

        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long noteId, String username){
        noteService.deleteNote(noteId, username);

        return "Note with ID " + noteId + " from user: " + username + " deleted successfully.";
    }

}
