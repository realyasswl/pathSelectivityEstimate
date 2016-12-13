rm end
rm start
rm labels
rm vertices
rm graph_sorted
sort -t"," -k 1,1n -k 2,2n graph >graph_sorted
cut -d , -f1 graph | sort -u -n>start
cut -d , -f2 graph | sort -u -n>labels
cut -d , -f3 graph | sort -u -n>end
sort -m start end>temp
sort -u temp>vertices
rm temp
