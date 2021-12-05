/**
 * Copyright TUSi Enterprise All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.src.roadsafety;


public class MirosList {

    private String Title;
    private String Article;
    private String Date;


    public MirosList() {
    }

    public MirosList(String title, String article, String date) {
        this.Title = title;
        this.Article = article;
        this.Date = date;

    }


    public void setTitle(String title) {
        this.Title = title;
    }
    public String getTitle() {
        return Title;
    }

    public void setArticle(String article) {
        this.Article = article;
    }
    public String getArticle() {
        return Article;
    }

    public void setDate(String date) {
        this.Date = date;
    }
    public String getDate() {
        return Date;
    }




}
