import sqlite3
from sqlite3 import Error

from sqlalchemy import insert

# Setting Up Database Connection
def create_connection(db_file):
    conn = None
    try:
        conn = sqlite3.connect(db_file)
    except Error as e:
        print(e)
    return conn

# Creating Table
def create_table(conn, create_table_sql):
    try:
        c = conn.cursor()
        c.execute(create_table_sql)
    except Error as e:
        print(e)

# Insert Movie
def insert_movie(conn, movie_data):
    sql = ''' INSERT INTO movies(movie_name,actor_name,actress_name,director_name,year_of_release)
              VALUES(?,?,?,?,?) '''
    cur = conn.cursor()
    cur.execute(sql, movie_data)
    conn.commit()
    return cur.lastrowid

# Selecting All Movies
def select_all_movies(conn):
    cur = conn.cursor()
    cur.execute("SELECT * FROM movies")

    rows = cur.fetchall()

    for row in rows:
        print(row)

def select_movie_by_actor_name(conn, actor_name):
    cur = conn.cursor()
    cur.execute("SELECT * FROM movies WHERE actor_name=?", (actor_name,))

    rows = cur.fetchall()

    for row in rows:
        print(row)

# Main fuction
def main():
    database = r"movies.db"

    sql_create_movies_table = """ CREATE TABLE IF NOT EXISTS movies (
                                        movie_id integer PRIMARY KEY,
                                        movie_name text NOT NULL,
                                        actor_name text NOT NULL,
                                        actress_name text NOT NULL,
                                        director_name text NOT NULL,
                                        year_of_release text NOT NULL
                                    ); """


    # create a database connection
    conn = create_connection(database)

    # create tables
    if conn is not None:
        # create movies table
        create_table(conn, sql_create_movies_table)

    else:
        print("Error! cannot create the database connection.")

    with conn:
        # inserting data into movies table
        # movie1 = ('Doctor Strange in the Multiverse of Madness','Benedict Cumberbatch','Elizabeth Olsen','Sam Raimi','2022')
        # movie2 = ('KGF2','Yash','Srinidhi Shetty','Prashanth Neel','2022')
        # movie3 = ('Iron Man','Robert Downey','Gwyneth Paltrow','Jon Favreau','2008')
        # movie4 = ('Spider Man Far From Home','Tom Holland','Zendaya','Jon Watts','2019')
        # movie5 = ('777 charlie','Rakshit Shetty','Sangeetha Sringeri','Kiranraj K','2022')
        # movie6 = ('KGF1','Yash','Srinidhi Shetty','Prashanth Neel','2018')

        # insert_movie(conn,movie1)
        # insert_movie(conn,movie2)
        # insert_movie(conn,movie3)
        # insert_movie(conn,movie4)
        # insert_movie(conn,movie5)
        # insert_movie(conn,movie6)

        print("1. Selecting All Movie Data")
        select_all_movies(conn)

         
        # print("2. Selecting Movie Name Based On Actor Name")
        # select_movie_by_actor_name(conn, 'Yash')
        



if __name__ == '__main__':
    main()