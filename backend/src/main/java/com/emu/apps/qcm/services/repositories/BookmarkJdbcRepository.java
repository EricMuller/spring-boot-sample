package com.emu.apps.qcm.services.repositories;

import com.emu.apps.qcm.model.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.sql.*;
import java.util.*;

@Repository
public class BookmarkJdbcRepository {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    protected JdbcTemplate jdbc;

    public Bookmark getBookmark(long id) {
        return jdbc.queryForObject("SELECT * FROM webmarks_bookmarks_bookmark WHERE node_ptr_id=?", bookmarkMapper, id);
    }

    public List<Bookmark> getBookmarks(long[] ids) {
        String inIds = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(ids));
        return jdbc.query("SELECT * FROM webmarks_bookmarks_bookmark WHERE node_ptr_id IN (" + inIds + ")", bookmarkMapper);
    }
    
    public List<Bookmark> getBookmarks() {
        return jdbc.query("SELECT * FROM mywebmarks_bookmark", bookmarkMapper);
    }
    
    private static final RowMapper<Bookmark> bookmarkMapper = new RowMapper<Bookmark>() {
        public Bookmark mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bookmark bookmark = new Bookmark(rs.getLong("node_ptr_id"), rs.getString("title"),rs.getString("url"),rs.getString("description"));
            return bookmark;
        }
    };

} 