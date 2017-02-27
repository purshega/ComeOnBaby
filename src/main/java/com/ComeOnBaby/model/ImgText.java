package com.ComeOnBaby.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Макс on 23.02.2017.
 */
@Entity
@Table(name = "imgtext")
public class ImgText implements Comparable<ImgText>{
    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name="text")
    private String text;

    @Column(name="img")
    private String img;

    @Column(name="type")
    private int type;

    //@NotEmpty
    @Column(name="sort")
    private int sort;

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)// - not working
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notice_id")
    private Notice notice;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public ImgText() {
    }

    @Override
    public boolean equals(Object o) {
        if(id==null)return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImgText imgText = (ImgText) o;

        return id.equals(imgText.id);
    }

    @Override
    public int hashCode() {
        try{
            return id.hashCode();
        }catch(Exception e){
            return 0;
        }
    }

    public ImgText(Long id, String text, String img, int sort, Notice notice) {
        this.id = id;
        this.text = text;
        this.img = img;
        this.sort = sort;
        this.notice = notice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public String getText() {
        return text;
    }

    public String getImg() {
        return img;
    }

    public int getSort() {
        return sort;
    }

    public Notice getNotice() {
        return notice;
    }

    @Override
    public int compareTo(ImgText obj) {
        int k = 0;
        if(obj!=null) k = obj.getSort();
        return sort - k;
    }

    @Override
    public String toString() {
        return "ImgText{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", img='" + img + '\'' +
                ", type=" + type +
                ", sort=" + sort +
                ", notice_id=" + notice.getId() +
                '}';
    }
}
