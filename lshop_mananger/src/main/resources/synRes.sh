#!/bin/sh
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/www testuser@10.0.1.158::tvpad --password-file=/etc/client/rsync.pwd
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/en testuser@10.0.1.158::tvpad --password-file=/etc/client/rsync.pwd
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/tvpadcn testuser@10.0.1.158::tvpad --password-file=/etc/client/rsync.pwd
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/tvpaden testuser@10.0.1.158::tvpad --password-file=/etc/client/rsync.pwd
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/bmcn testuser@10.0.1.162::banana --password-file=/etc/client/rsync.pwd
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/bmen testuser@10.0.1.162::banana --password-file=/etc/client/rsync.pwd
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/bscn testuser@10.0.1.162::banana --password-file=/etc/client/rsync.pwd
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/bsen testuser@10.0.1.162::banana --password-file=/etc/client/rsync.pwd
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/mbmcn testuser@10.0.1.162::banana --password-file=/etc/client/rsync.pwd
/usr/bin/rsync -av /www/www.itvpad.com/www/htdocs/web/mbscn testuser@10.0.1.162::banana --password-file=/etc/client/rsync.pwd