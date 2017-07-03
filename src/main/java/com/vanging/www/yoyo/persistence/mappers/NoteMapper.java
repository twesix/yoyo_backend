package com.vanging.www.yoyo.persistence.mappers;

import com.vanging.www.yoyo.persistence.models.Note;

public interface NoteMapper
{
    public Note findById(String note_id);
}
