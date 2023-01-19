package uk.co.raubach.tractivity.server.pojo;

public class PaginatedResult<T>
{
	private T    data;
	private long count = 0L;

	public PaginatedResult()
	{
	}

	public PaginatedResult(T data, long count)
	{
		this.data = data;
		this.count = count;
	}

	public T getData()
	{
		return this.data;
	}

	public PaginatedResult<T> setData(T data)
	{
		this.data = data;
		return this;
	}

	public long getCount()
	{
		return this.count;
	}

	public PaginatedResult<T> setCount(long count)
	{
		this.count = count;
		return this;
	}

	public String toString()
	{
		return "PaginatedResult{data=" + this.data + ", count=" + this.count + "}";
	}
}