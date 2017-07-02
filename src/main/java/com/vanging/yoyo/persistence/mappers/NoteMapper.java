package com.vanging.yoyo.persistence.mappers;

import com.vanging.yoyo.persistence.models.Note;

public interface NoteMapper
{
    public Note findById(String note_id);
}
